package com.dahantc.service;

import com.dahantc.bean.ProductListData;
import com.dahantc.common.DecreaseStockInput;
import com.dahantc.common.ProductInfoOutput;
import com.dahantc.vo.ResultVO;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 16:30 .
 */
public interface ProductListService {
    ResultVO<List<ProductListData>> findProductListInfo();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
