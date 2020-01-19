package com.dahantc.controller;

import com.dahantc.bean.ProductListData;
import com.dahantc.common.DecreaseStockInput;
import com.dahantc.common.ProductInfoOutput;
import com.dahantc.service.ProductListService;
import com.dahantc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 18:40 .
 */
@RestController
@RequestMapping("product")
public class ProductListController {
    @Autowired
    private ProductListService productListService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("list")
    public ResultVO<List<ProductListData>> findProductList() {
        return productListService.findProductListInfo();
    }

    /**
     * 获取商品列表(给订单服务用的)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productListService.findList(productIdList);
    }
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productListService.decreaseStock(decreaseStockInputList);
    }
}
