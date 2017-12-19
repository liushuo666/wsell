package com.liushuo.wsell.dto;

import lombok.Data;

/**
 * Create by liushuo on 2017/11/30.
 */
@Data
public class CartDto {
    private  String productId;
    private  Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
