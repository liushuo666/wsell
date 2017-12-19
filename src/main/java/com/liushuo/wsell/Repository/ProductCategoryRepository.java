package com.liushuo.wsell.Repository;

import com.liushuo.wsell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> list);
}
