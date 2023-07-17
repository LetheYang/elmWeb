package com.elm.dao.impl;

import com.elm.dao.BusinessDao;
import com.elm.entity.Business;
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
public class BusinessDaoImpl implements BusinessDao {
    @Override
    public List<Business> queryAllBusiness() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Business> businessList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM business");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Business business = new Business();
                business.setBusinessId(resultSet.getInt("businessId"));
                business.setBusinessName(resultSet.getString("businessName"));
                business.setBusinessAddress(resultSet.getString("businessAddress"));
                business.setBusinessExplain(resultSet.getString("businessExplain"));
                business.setBusinessImg(resultSet.getString("businessImg"));
                business.setOrderTypeId(resultSet.getInt("orderTypeId"));
                business.setStarPrice(resultSet.getDouble("starPrice"));
                business.setDeliveryPrice(resultSet.getDouble("deliveryPrice"));
                business.setRemarks(resultSet.getString("remarks"));
                businessList.add(business);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return businessList;
    }

    @Override
    public List<Business> queryBusinessByType(int orderTypeId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Business> businessList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM business WHERE orderTypeId = ?");
            preparedStatement.setInt(1, orderTypeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Business business = new Business();
                business.setBusinessId(resultSet.getInt("businessId"));
                business.setBusinessName(resultSet.getString("businessName"));
                business.setBusinessAddress(resultSet.getString("businessAddress"));
                business.setBusinessExplain(resultSet.getString("businessExplain"));
                business.setBusinessImg(resultSet.getString("businessImg"));
                business.setOrderTypeId(resultSet.getInt("orderTypeId"));
                business.setStarPrice(resultSet.getDouble("starPrice"));
                business.setDeliveryPrice(resultSet.getDouble("deliveryPrice"));
                business.setRemarks(resultSet.getString("remarks"));
                businessList.add(business);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return businessList;
    }

    @Override
    public Business queryBusinessById(int businessId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Business business = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM business WHERE businessId = ?");
            preparedStatement.setInt(1, businessId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                business = new Business();
                business.setBusinessId(resultSet.getInt("businessId"));
                business.setBusinessName(resultSet.getString("businessName"));
                business.setBusinessAddress(resultSet.getString("businessAddress"));
                business.setBusinessExplain(resultSet.getString("businessExplain"));
                business.setBusinessImg(resultSet.getString("businessImg"));
                business.setOrderTypeId(resultSet.getInt("orderTypeId"));
                business.setStarPrice(resultSet.getDouble("starPrice"));
                business.setDeliveryPrice(resultSet.getDouble("deliveryPrice"));
                business.setRemarks(resultSet.getString("remarks"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return business;
    }
}
