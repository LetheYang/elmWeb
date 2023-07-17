package com.elm.dao;

import com.elm.entity.OrderDetailet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author akemihomurasama
 */
public interface OrderDetailetDao {
    /**
     * 向orderDetailet表中添加数据
     * @param orderDetailet 要添加的数据
     * @return 添加结果
     */
    int addToOrderDetailet(OrderDetailet orderDetailet) throws SQLException;

    /**
     * 根据订单id 查询订单明细
     * @param orderId 订单ID
     * @return 订单明细
     */
    List<OrderDetailet> queryOrderDetaByOrderId(int orderId) throws SQLException;

    /**
     * 根据订单id 查询食品id列表
     * @param orderId 订单id
     * @return id列表
     * @throws SQLException 异常
     */
    List<Integer> queryFoodIdListByOrderId(int orderId) throws SQLException;

}
