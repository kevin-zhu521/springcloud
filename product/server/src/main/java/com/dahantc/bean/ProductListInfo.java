package com.dahantc.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by Kerry Zhu on 2018/10/21 15:38 .
 */
@Data
public class ProductListInfo {
    private String code;
    private String msg;
    private List<ProductListData> data;


}
