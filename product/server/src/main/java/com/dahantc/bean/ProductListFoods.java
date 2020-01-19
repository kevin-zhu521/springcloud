package com.dahantc.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Kerry Zhu on 2018/10/21 15:42 .
 */
@Data
public class ProductListFoods {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("Icon")
    private String productIcon;
}
