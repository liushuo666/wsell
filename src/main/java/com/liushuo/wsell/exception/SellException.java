package com.liushuo.wsell.exception;

import com.liushuo.wsell.enums.ResultEnum;

/**
 * Create by liushuo on 2017/11/30.
 */
public class SellException extends RuntimeException {
    private  Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }
}
