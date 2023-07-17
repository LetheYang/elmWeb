package com.elm.dao;

import com.elm.entity.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public interface OrdersDao {
    /**
     * 查询当前用户的所有订单
     * @param userId 用户id
     * @return 用户所有订单
     */
    List<Orders> queryOrdersById(int userId) throws SQLException;

    /**
     * 向订单表中添加订单
     * @param orders 订单数据
     * @return 添加结果
     */
    int addOrder(Orders orders) throws SQLException;
    /**
     * 根据订单id查询订单
     * @param orderId 订单id
     * @return 订单信息
     */
    Orders queryOrderByOrderId(int orderId) throws SQLException;

    /**
     * 用户付款后设置订单状态为已付款
     * @param orderId 订单id
     * @return 返回更新结果
     */
    int upDateOrderStatus(int orderId) throws SQLException;
}
