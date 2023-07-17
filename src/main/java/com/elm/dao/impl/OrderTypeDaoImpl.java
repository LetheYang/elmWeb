package com.elm.dao.impl;

import com.elm.dao.OrderTypeDao;
import com.elm.entity.OrderType;
import com.elm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderTypeDaoImpl implements OrderTypeDao {
    @Override
    public List<OrderType> findAllorderType() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OrderType> orderTypeList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM ordertype");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderType orderType = new OrderType();
                orderType.setOrderTypeId(resultSet.getInt("ordertypeid"));
                orderType.setOrderTypeImg(resultSet.getString("ordertypeimg"));
                orderType.setOrderTypeName(resultSet.getString("ordertypename"));
                orderTypeList.add(orderType);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return orderTypeList;
    }
}
