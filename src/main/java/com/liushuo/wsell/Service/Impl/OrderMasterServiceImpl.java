package com.liushuo.wsell.Service.Impl;

import com.liushuo.wsell.Repository.OrderDetailRepository;
import com.liushuo.wsell.Repository.OrderMasterRepository;
import com.liushuo.wsell.Service.OrderMasterService;
import com.liushuo.wsell.Service.ProductService;
import com.liushuo.wsell.converter.OrdermaserToOrdermasterDtoConverter;
import com.liushuo.wsell.dataobject.OrderDetail;
import com.liushuo.wsell.dataobject.OrderMaster;
import com.liushuo.wsell.dataobject.ProductInfo;
import com.liushuo.wsell.dto.CartDto;
import com.liushuo.wsell.dto.OrderDto;
import com.liushuo.wsell.enums.OrderStatusEnum;
import com.liushuo.wsell.enums.PayStatusEnum;
import com.liushuo.wsell.enums.ResultEnum;
import com.liushuo.wsell.exception.SellException;
import com.liushuo.wsell.utils.KeyUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by liushuo on 2017/11/30.
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderDto creatOrder(OrderDto orderDto) {
        BigDecimal count = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.getUniqueKey();
        for (OrderDetail orderDetail:orderDto.getOrderDetailList() ) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo==null){
                throw new  SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            count=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(count);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(count);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);

        return orderDto;

    }

    @Override
        public OrderDto findOrder(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (orderDetailList.isEmpty()){
            throw new SellException(ResultEnum.ORDERDETAIO_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrdermaserToOrdermasterDtoConverter.conver(orderMasterPage.getContent());
        Page<OrderDto> orderDtos = new PageImpl<OrderDto>(orderDtoList,pageable,orderDtoList.size());
        return orderDtos;
    }

    @Override
    public OrderDto cancle(OrderDto orderDto) {
        

        return null;

    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
