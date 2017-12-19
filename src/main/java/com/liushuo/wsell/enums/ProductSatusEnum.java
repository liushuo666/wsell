package com.liushuo.wsell.enums;

import lombok.Getter;

/**
 * Create by liushuo on 2017/11/28.
 */
@Getter
public enum ProductSatusEnum {
    UP(0,"上架中"),
    DOWN(1,"已下架")
    ;
    private Integer code;
    private String message;

    ProductSatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
