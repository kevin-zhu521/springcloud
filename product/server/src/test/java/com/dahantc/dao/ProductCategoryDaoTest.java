package com.dahantc.dao;

import com.dahantc.ProductApplicationTests;
import com.dahantc.domain.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 16:25 .
 */
public class ProductCategoryDaoTest extends ProductApplicationTests{
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void findByCategoryTypeIn() throws Exception {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        List<ProductCategory> productCategoryList= productCategoryDao.findByCategoryTypeIn(al);
        System.out.println(productCategoryList);
    }

}