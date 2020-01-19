package com.dahantc.service;


import com.dahantc.dto.OrderDTO;

/**
 * Created by Kevin Zhu on 2018/10/21 21:01 .
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单(只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
