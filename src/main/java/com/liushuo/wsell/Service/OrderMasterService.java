package com.liushuo.wsell.Service;

import com.liushuo.wsell.dataobject.OrderMaster;
import com.liushuo.wsell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create by liushuo on 2017/11/30.
 */
public interface OrderMasterService {

    OrderDto creatOrder(OrderDto orderDto);

    OrderDto findOrder(String orderId);

    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    OrderDto cancle(OrderDto orderDto);

    OrderDto finish(OrderDto orderDto);

    OrderDto paid(OrderDto orderDto);

}
