package com.elm.controller;

import com.elm.utils.JDBCUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class Test {
    public void test(HttpServletRequest request) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int orderId = -1;
        try {
            JDBCUtil.beginTransaction();
            preparedStatement = connection.prepareStatement("Insert INTO orders  (userId,businessId,orderDate,orderTotal,daId,orderState) values (1,10001,NOW(),11,10,1)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
            System.out.println(orderId);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(resultSet,preparedStatement);
            JDBCUtil.close();
        }
    }
}


