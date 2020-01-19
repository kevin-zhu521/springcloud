package com.dahantc.Enum;

import lombok.Getter;

/**
 * Created by Kerry Zhu on 2018/10/21 15:51 .
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWM(1,"下架");

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
