package com.elm.service;

import com.elm.entity.OrderDetailet;

import java.util.List;

/**
 * @author akemihomurasama
 */
public interface OrderDetailetService {
    /**
     * 根据订单id 查询订单明细
     *
     * @param orderId 订单ID
     * @return 订单明细
     */
    List<OrderDetailet> queryOrderDetaByOrderId(int orderId);
}
