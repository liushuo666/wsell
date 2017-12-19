package com.liushuo.wsell.Service.Impl;

import com.liushuo.wsell.Service.ProductService;
import com.liushuo.wsell.dataobject.ProductInfo;
import com.liushuo.wsell.enums.ProductSatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liushuo on 2017/11/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("879987");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductInfo> list = productService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll1() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> infos = productService.findAll(pageRequest);
        System.out.println( infos.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("879987");
        productInfo.setProductName("芝士蛋糕");
        productInfo.setProductPrice(new BigDecimal(156));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的蛋糕");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

}