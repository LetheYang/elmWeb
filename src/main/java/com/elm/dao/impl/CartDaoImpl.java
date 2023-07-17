package com.elm.dao.impl;

import com.elm.dao.CartDao;
import com.elm.entity.Business;
import com.elm.entity.Cart;
import com.elm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    /**
     * 查询当前用户在所有商家中的购物车的数量
     *
     * @param userId 用户id
     * @return 当前商家的购物车内容
     */
    @Override
    public Map<Integer, Integer> queryFoodCountInCart(String userId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Integer> map = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT businessId,  SUM(quantity) FROM cart WHERE userId = ? GROUP BY businessId");
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getInt("businessId"), resultSet.getInt("SUM(quantity)"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return map;
    }

    /**
     * 查询一个商家中的购物车信息
     *
     * @param userId     用户id
     * @param businessId 商家id
     * @return 用户购物车内容
     */
    @Override
    public Map<Integer, Integer> queryFoodInBusiness(String userId, Integer businessId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Integer, Integer> map = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT foodId,  quantity FROM cart WHERE userId = ? AND businessId =?");
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, businessId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getInt("foodId"), resultSet.getInt("quantity"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return map;
    }

    /**
     * 向购物车添加商品，设置数量为1
     *
     * @param businessId 商家id
     * @param userId     用户id
     * @param foodId     食品id
     * @return 添加结果
     */
    @Override
    public int addToCart(int businessId, int userId, int foodId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO cart (businessId, userId, foodId,quantity) values (?,?,?,1)");
            preparedStatement.setInt(1, businessId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, foodId);
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(connection, preparedStatement);
        }

    }

    /**
     * 增加购物车中商品的数量
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 返回增加结果
     */
    @Override
    public int increaseFoodInCart(int userId, int foodId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE cart SET quantity=quantity+1 where userId = ? and foodId =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, foodId);
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 减少购物车中食品数量
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 修改结果
     */
    @Override
    public int decreaseFoodInCart(int userId, int foodId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE cart SET quantity=quantity-1 where userId = ? and foodId =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, foodId);
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 从数据库中移除食品信息
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 修改信息
     */
    @Override
    public int removeFoodFromCart(int userId, int foodId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM cart where userId = ? and foodId =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, foodId);
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(preparedStatement);
        }
    }
}
