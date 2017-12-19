package com.liushuo.wsell.Service;

import com.liushuo.wsell.dataobject.OrderDetail;
import org.hibernate.criterion.Order;

/**
 * Create by liushuo on 2017/11/30.
 */
public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
}
