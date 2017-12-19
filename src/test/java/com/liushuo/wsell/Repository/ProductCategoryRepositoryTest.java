package com.liushuo.wsell.Repository;

import com.liushuo.wsell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findOne(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }
    @Test
    @Transactional  //测试的数据不入库注解
    public void saveCategory(){
        ProductCategory productCategory = new ProductCategory("爱吃",5);
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void updateCategory(){
        ProductCategory productCategory = productCategoryRepository.findOne(3);
        productCategory.setCategoryType(4);
        productCategoryRepository.save(productCategory);
    }
    @Test
    public void deleteCategory(){
        productCategoryRepository.delete(3);
    }
    @Test
    public void findListByType(){
        List<Integer> list = Arrays.asList(2,3);
        List<ProductCategory> resultList = productCategoryRepository.findAllByCategoryTypeIn(list);
        Assert.assertNotEquals(0,resultList.size());
    }
}