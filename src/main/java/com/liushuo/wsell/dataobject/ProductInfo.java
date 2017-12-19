package com.liushuo.wsell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Create by liushuo on 2017/11/27.
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;
    /** 名字*/
    private String productName;
    /** 单价*/
    private BigDecimal productPrice;

    /** 库存 */
    private  Integer productStock;

    /** 描述 */
    private String productDescription;

    private  String productIcon;
    /** 状态，0正常1下架 */
    private Integer productStatus;

    private  Integer categoryType;
}
