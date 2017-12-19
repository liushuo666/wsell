package com.liushuo.wsell.Service.Impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liushuo.wsell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by liushuo on 2017/11/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(2);
        Assert.assertEquals(new Integer(2),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = categoryService.findAll();
        // Assert.assertEquals(4,list.size());
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findCategorysByType() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> productCategoryList = categoryService.findCategorysByType(list);
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("小孩子最爱",6);
        ProductCategory category = categoryService.save(productCategory);
        Assert.assertEquals(new Integer(6),category.getCategoryId());
    }

}