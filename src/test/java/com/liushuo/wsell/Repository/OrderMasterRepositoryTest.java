package com.liushuo.wsell.Repository;

import com.liushuo.wsell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Create by liushuo on 2017/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("11113");
        orderMaster.setBuyerName("小花");
        orderMaster.setBuyerPhone("15241255434");
        orderMaster.setBuyerAddress("深圳市福田区交通银行大厦");
        orderMaster.setBuyerOpenid("110111");
        orderMaster.setOrderAmount(new BigDecimal(21));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findOrderByOpenId(){
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("110111", pageRequest);
        Assert.assertNotEquals(0,result.getSize());
    }
}