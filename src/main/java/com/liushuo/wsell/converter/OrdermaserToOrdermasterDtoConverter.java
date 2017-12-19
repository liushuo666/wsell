package com.liushuo.wsell.converter;

import com.liushuo.wsell.dataobject.OrderMaster;
import com.liushuo.wsell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by liushuo on 2017/12/4.
 */
public class OrdermaserToOrdermasterDtoConverter {
    public static OrderDto conver(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> conver(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e ->
                conver(e)
        ).collect(Collectors.toList());
    }
}
