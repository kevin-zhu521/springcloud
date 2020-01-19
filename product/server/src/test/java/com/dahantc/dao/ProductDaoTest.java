package com.dahantc.dao;

import com.dahantc.Enum.ProductStatusEnum;
import com.dahantc.ProductApplicationTests;
import com.dahantc.domain.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 15:49 .
 */
public class ProductDaoTest extends ProductApplicationTests{
    @Autowired
    private ProductDao  productDao;
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> lists = productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
        System.out.println(lists);
    }

}