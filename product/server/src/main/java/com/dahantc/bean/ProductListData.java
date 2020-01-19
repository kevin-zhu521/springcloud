package com.dahantc.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 15:40 .
 */
@Data
public class ProductListData {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductListFoods> foods;


}
