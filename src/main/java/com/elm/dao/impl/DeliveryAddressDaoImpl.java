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

    /**
     * 根据用户id查询地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    @Override
    public List<DeliveryAddress> queryAddressListByUserId(int userId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<DeliveryAddress> deliveryAddressList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM deliveryaddress WHERE userId = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setUserId(userId);
                deliveryAddress.setDaId(resultSet.getInt("daId"));
                deliveryAddress.setContactName(resultSet.getString("contactName"));
                deliveryAddress.setContactTel(resultSet.getString("contactTel"));
                deliveryAddress.setAddress(resultSet.getString("address"));
                deliveryAddress.setContactSex(resultSet.getInt("contactSex"));
                deliveryAddressList.add(deliveryAddress);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return deliveryAddressList;
    }

    /**
     * 根据地址id查询地址
     *
     * @param daId 地址id
     * @return 用户地址
     */
    @Override
    public DeliveryAddress queryAddressByDaId(int daId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM deliveryaddress WHERE daId = ?");
            preparedStatement.setInt(1, daId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deliveryAddress.setUserId(resultSet.getInt("userId"));
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

    /**
     * 用户添加地址
     *
     * @param deliveryAddress 地址
     * @return 添加结果
     */
    @Override
    public int addDeliveryAddress(DeliveryAddress deliveryAddress) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO deliveryaddress  (contactName,contactSex,contactTel,address,userId) values (?,?,?,?,?) ");
            preparedStatement.setString(1, deliveryAddress.getContactName());
            preparedStatement.setInt(2, deliveryAddress.getContactSex());
            preparedStatement.setString(3, deliveryAddress.getContactTel());
            preparedStatement.setString(4, deliveryAddress.getAddress());
            preparedStatement.setInt(5, deliveryAddress.getUserId());
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(preparedStatement);
        }
    }
}
