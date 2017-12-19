package com.liushuo.wsell.Repository;

import com.liushuo.wsell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by liushuo on 2017/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDtailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public  void saveOrderDetailTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("99998");
        orderDetail.setOrderId("11112");
        orderDetail.setProductIcon("http://gggg.jpg");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(3.0));
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findOrderDetailByOrderId(){
        List<OrderDetail> resultList = orderDetailRepository.findByOrderId("11112");
        Assert.assertNotEquals(0,resultList.size());
    }
}