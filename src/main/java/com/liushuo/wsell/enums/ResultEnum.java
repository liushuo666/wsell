package com.liushuo.wsell.enums;

import lombok.Getter;

/**
 * Create by liushuo on 2017/11/30.
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存已售馨"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIO_NOT_EXIST(13,"无订单明细"),
    ;

    private  Integer code;
    private  String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
