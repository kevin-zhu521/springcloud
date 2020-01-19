package com.dahantc.dao;

import com.dahantc.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 16:20 .
 */
@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
