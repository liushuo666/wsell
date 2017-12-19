package com.liushuo.wsell.Service;

import com.liushuo.wsell.dataobject.ProductInfo;
import com.liushuo.wsell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * Create by liushuo on 2017/11/28.
 */
public interface ProductService {

    ProductInfo findOne(String id);

    List<ProductInfo> findAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}
