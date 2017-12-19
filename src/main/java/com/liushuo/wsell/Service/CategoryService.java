package com.liushuo.wsell.Service;

import com.liushuo.wsell.dataobject.ProductCategory;

import java.util.List;

/**
 * Create by liushuo on 2017/11/27.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findCategorysByType(List<Integer> list);

    ProductCategory save(ProductCategory productCategory);

}
