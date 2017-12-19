package com.liushuo.wsell.Service.Impl;

import com.liushuo.wsell.Repository.ProductInfoRepository;
import com.liushuo.wsell.Service.ProductService;
import com.liushuo.wsell.dataobject.ProductInfo;
import com.liushuo.wsell.dto.CartDto;
import com.liushuo.wsell.enums.ProductSatusEnum;
import com.liushuo.wsell.enums.ResultEnum;
import com.liushuo.wsell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by liushuo on 2017/11/28.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findAll() {
        return  productInfoRepository.findAll();
       // return productInfoRepository.findByProductStatus(ProductSatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto:cartDtoList){
            ProductInfo result = productInfoRepository.findOne(cartDto.getProductId());
            if (result==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = result.getProductStock()-cartDto.getProductQuantity();
            if (stock<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            result.setProductStock(stock);
            productInfoRepository.save(result);
        }
    }
}
