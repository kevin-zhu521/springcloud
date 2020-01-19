package com.dahantc.service.impl;

import com.dahantc.Enum.ProductStatusEnum;
import com.dahantc.Enum.ResultEnum;
import com.dahantc.bean.ProductListData;
import com.dahantc.bean.ProductListFoods;
import com.dahantc.common.DecreaseStockInput;
import com.dahantc.common.ProductInfoOutput;
import com.dahantc.dao.ProductCategoryDao;
import com.dahantc.dao.ProductDao;
import com.dahantc.domain.ProductCategory;
import com.dahantc.domain.ProductInfo;
import com.dahantc.exception.ProductException;
import com.dahantc.service.ProductListService;
import com.dahantc.utils.JsonUtil;
import com.dahantc.utils.ResultVOUtil;
import com.dahantc.vo.ResultVO;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Kerry Zhu on 2018/10/21 16:35 .
 */
@Service
public class ProductListServiceImpl implements ProductListService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public ResultVO<List<ProductListData>> findProductListInfo() {
        List<ProductInfo> productInfoList = productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(categoryTypeList);

        List<ProductListData> productListDataList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            List<ProductListFoods> productListFoodsList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType() == productInfo.getCategoryType()) {
                    ProductListFoods productListFoods = new ProductListFoods();
                    BeanUtils.copyProperties(productInfo, productListFoods);
                    productListFoodsList.add(productListFoods);

                }
            }
            ProductListData productListData = new ProductListData();
            productListData.setCategoryName(productCategory.getCategoryName());
            productListData.setCategoryType(productCategory.getCategoryType());
            productListData.setFoods(productListFoodsList);
            productListDataList.add(productListData);
        }

        return ResultVOUtil.success(productListDataList);
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productDao.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfos = decreaseStockProcess(decreaseStockInputList);
        //转换
        List<ProductInfoOutput> productInfoOutputs= productInfos.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputs));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productDao.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productDao.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
