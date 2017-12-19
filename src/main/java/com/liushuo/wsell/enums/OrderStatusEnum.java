package com.liushuo.wsell.enums;

import lombok.Getter;

/**
 * Create by liushuo on 2017/11/29.
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"已完成订单"),
    CANCLE(2,"取消订单")
    ;
    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
