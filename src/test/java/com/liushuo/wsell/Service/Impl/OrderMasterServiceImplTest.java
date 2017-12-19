package com.liushuo.wsell.Service.Impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liushuo.wsell.Service.OrderMasterService;
import com.liushuo.wsell.dataobject.OrderDetail;
import com.liushuo.wsell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liushuo on 2017/11/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;
    private  final  String BUYER_OPENID = "110111";
    @Test
    public void creatOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
            orderDto.setBuyerName("小花");
            orderDto.setBuyerAddress("深圳是福田区交通银行大厦");
            orderDto.setBuyerPhone("15241255434");
            orderDto.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId("123456");
            orderDetail.setProductQuantity(1);

            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setProductId("879987");
            orderDetail1.setProductQuantity(2);
            orderDetailList.add(orderDetail);
            orderDetailList.add(orderDetail1);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto rusult = orderMasterService.creatOrder(orderDto);
        log.info("新建订单result={}",rusult);

    }

    @Test
    public void findOrder() throws Exception {
        OrderDto result = orderMasterService.findOrder("1512032967751915242");
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderDto> list = orderMasterService.findList("110111", pageRequest);
        Assert.assertNotEquals(0,list.getSize());
    }

    @Test
    public void cancle() throws Exception {

    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}