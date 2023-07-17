package com.elm.test;

import com.elm.utils.JDBCUtil;

import java.sql.*;

public class Test {
    public void test() {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int orderId = -1;
        try {
            JDBCUtil.beginTransaction();
            preparedStatement = connection.prepareStatement("Insert INTO orders  (userId,businessId,orderDate,orderTotal,daId,orderState) values (1,10001,NOW(),11,10,1)", Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                orderId = generatedKeys.getInt("orderId");
                JDBCUtil.close(preparedStatement);
            }
            JDBCUtil.commitTransaction();
            System.out.println(orderId);
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(preparedStatement);
            JDBCUtil.close();
        }
    }
}


