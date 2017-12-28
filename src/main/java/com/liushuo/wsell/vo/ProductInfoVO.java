package com.liushuo.wsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Create by liushuo on 2017/11/28.
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private  String productId;

    @JsonProperty("name")
    private  String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private  String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}

