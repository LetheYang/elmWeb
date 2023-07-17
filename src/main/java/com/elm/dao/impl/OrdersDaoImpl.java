package com.elm.dao.impl;

import com.elm.dao.OrdersDao;
import com.elm.entity.Cart;
import com.elm.entity.Orders;
import com.elm.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    /**
     * 查询当前用户的所有订单
     *
     * @param userId 用户id
     * @return 用户所有订单
     */
    @Override
    public List<Orders> queryOrdersById(int userId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Orders> ordersList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM orders WHERE userId = ? ");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setBusinessId(resultSet.getInt("businessId"));
                order.setOrderDate(resultSet.getString("orderDate"));
                order.setOrderTotal(resultSet.getDouble("orderTotal"));
                order.setDaId(resultSet.getInt("daId"));
                order.setOrderState(resultSet.getInt("orderState"));
                ordersList.add(order);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return ordersList;
    }

    /**
     * 向订单表中添加订单
     *
     * @param orders 订单数据
     * @return 添加结果
     */
    @Override
    public int addOrder(Orders orders) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int orderId = -1;
        try {
            preparedStatement = connection.prepareStatement("Insert INTO orders  (userId,businessId,orderDate,orderTotal,daId,orderState) values (?,?,NOW(),?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, orders.getUserId());
            preparedStatement.setInt(2, orders.getBusinessId());
            preparedStatement.setDouble(3, orders.getOrderTotal());
            preparedStatement.setInt(4, orders.getDaId());
            preparedStatement.setInt(5, orders.getOrderState());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return orderId;
    }

    /**
     * 根据订单id查询订单
     *
     * @param orderId 订单id
     * @return 订单信息
     */
    @Override
    public Orders queryOrderByOrderId(int orderId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Orders order = new Orders();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM orders WHERE orderId = ? ");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setBusinessId(resultSet.getInt("businessId"));
                order.setOrderDate(resultSet.getString("orderDate"));
                order.setOrderTotal(resultSet.getDouble("orderTotal"));
                order.setDaId(resultSet.getInt("daId"));
                order.setOrderState(resultSet.getInt("orderState"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return order;
    }

    /**
     * 用户付款后设置订单状态为已付款
     *
     * @param orderId 订单id
     * @return 返回更新结果
     */
    @Override
    public int upDateOrderStatus(int orderId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE orders SET orderState = 1 WHERE orderId=?");
            preparedStatement.setInt(1, orderId);
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(preparedStatement);
        }
    }
}
