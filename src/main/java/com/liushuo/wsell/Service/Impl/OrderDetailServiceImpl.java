package com.liushuo.wsell.Service.Impl;

import com.liushuo.wsell.Repository.OrderDetailRepository;
import com.liushuo.wsell.Service.OrderDetailService;
import com.liushuo.wsell.dataobject.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by liushuo on 2017/11/30.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
