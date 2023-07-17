package com.elm.service;

import com.elm.entity.OrderDetailet;
import com.elm.entity.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public interface OrdersService {
    /**
     * 查询当前用户的所有订单
     * @param userId 用户id
     * @return 用户所有订单
     */
    List<Orders> queryOrdersById(int userId);

    /**
     * 用户结算
     * @param orders 用户订单明细
     * @param orderDetailetList 用户订单中食品列表
     * @return 添加结果
     */
    int confirmOrder(Orders orders, List<OrderDetailet> orderDetailetList);

    /**
     * 根据订单id查询订单
     * @param orderId 订单id
     * @return 订单信息
     */
    Orders queryOrderByOrderId(int orderId);
    /**
     * 用户付款后设置订单状态为已付款
     * @param orderId 订单id
     * @return 返回更新结果
     */
    int upDateOrderStatus(int orderId);
}
