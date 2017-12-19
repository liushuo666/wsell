package com.liushuo.wsell.dto;

import com.liushuo.wsell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Create by liushuo on 2017/11/30.
 */
@Data
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus ;

    private Integer payStatus ;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList ;
}
