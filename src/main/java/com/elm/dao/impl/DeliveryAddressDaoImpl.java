package com.elm.dao.impl;

import com.elm.dao.DeliveryAddressDao;
import com.elm.entity.Business;
import com.elm.entity.DeliveryAddress;
import com.elm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
    /**
     * 根据用户id查询用户地址
     *
     * @param userId 用户id
     * @return 用户地址
     */
    @Override
    public DeliveryAddress queryAddressByUserId(int userId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM deliveryaddress WHERE userId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deliveryAddress.setUserId(userId);
                deliveryAddress.setDaId(resultSet.getInt("daId"));
                deliveryAddress.setContactName(resultSet.getString("contactName"));
                deliveryAddress.setContactTel(resultSet.getString("contactTel"));
                deliveryAddress.setAddress(resultSet.getString("address"));
                deliveryAddress.setContactSex(resultSet.getInt("contactSex"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return deliveryAddress;
    }
}
