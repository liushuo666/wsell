package com.liushuo.wsell.vo;

import lombok.Data;

/**
 * Create by liushuo on 2017/11/28.
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
