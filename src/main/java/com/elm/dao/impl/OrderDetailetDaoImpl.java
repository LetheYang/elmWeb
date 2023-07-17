package com.elm.dao.impl;

import com.elm.dao.OrderDetailetDao;
import com.elm.entity.OrderDetailet;
import com.elm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailetDaoImpl implements OrderDetailetDao {
    /**
     * 向orderDetailet表中添加数据
     *
     * @param orderDetailet 要添加的数据
     * @return 添加结果
     */
    @Override
    public int addToOrderDetailet(OrderDetailet orderDetailet) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("Insert INTO orderdetailet  (orderId,foodId,quantity) values (?,?,?)");
            preparedStatement.setInt(1, orderDetailet.getOrderId());
            preparedStatement.setInt(2, orderDetailet.getFoodId());
            preparedStatement.setInt(3, orderDetailet.getQuantity());
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(preparedStatement);
        }
    }

    /**
     * 根据订单id 查询订单明细
     *
     * @param orderId 订单ID
     * @return 订单明细
     */
    @Override
    public List<OrderDetailet> queryOrderDetaByOrderId(int orderId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OrderDetailet> orderDetailetList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM orderdetailet WHERE orderId=?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetailet orderDetailet = new OrderDetailet();
                orderDetailet.setOrderId(resultSet.getInt("orderId"));
                orderDetailet.setFoodId(resultSet.getInt("foodId"));
                orderDetailet.setOdId(resultSet.getInt("odId"));
                orderDetailet.setQuantity(resultSet.getInt("quantity"));
                orderDetailetList.add(orderDetailet);
            }
        } finally {
            JDBCUtil.close(resultSet,preparedStatement);
        }
        return orderDetailetList;
    }

    /**
     * 根据订单id 查询食品id列表
     *
     * @param orderId 订单id
     * @return id列表
     * @throws SQLException 异常
     */
    @Override
    public List<Integer> queryFoodIdListByOrderId(int orderId) throws SQLException {
        return null;
    }
}
