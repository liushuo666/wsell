package com.liushuo.wsell.enums;

import lombok.Getter;

/**
 * Create by liushuo on 2017/11/29.
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;
    private  Integer code;
    private  String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
