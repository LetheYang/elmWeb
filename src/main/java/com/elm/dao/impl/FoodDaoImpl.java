package com.elm.dao.impl;

import com.elm.dao.FoodDao;
import com.elm.entity.Business;
import com.elm.entity.Food;
import com.elm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    @Override
    public List<Food> queryFoodByBusiness(int businessId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Food> foodList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT  foodId,foodName,foodExplain,foodImg,foodPrice,remarks FROM food WHERE businessId = ?");
            preparedStatement.setInt(1, businessId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();
                food.setFoodId(resultSet.getInt("foodId"));
                food.setFoodName(resultSet.getString("foodName"));
                food.setFoodExplain(resultSet.getString("foodExplain"));
                food.setFoodImg(resultSet.getString("foodImg"));
                food.setFoodPrice(resultSet.getDouble("foodPrice"));
                food.setBusinessId(businessId);
                food.setRemarks(resultSet.getString("remarks"));
                foodList.add(food);
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return foodList;
    }

    /**
     * 根据食品id查询食品信息
     *
     * @param foodId 食品id
     * @return 食品
     */
    @Override
    public Food queryFoodByFoodId(int foodId) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Food food = new Food();
        try {
            preparedStatement = connection.prepareStatement("SELECT  foodId,foodName,foodExplain,foodPrice,remarks,businessId FROM food WHERE foodId = ?");
            preparedStatement.setInt(1, foodId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                food.setFoodId(resultSet.getInt("foodId"));
                food.setFoodName(resultSet.getString("foodName"));
                food.setFoodExplain(resultSet.getString("foodExplain"));
                food.setFoodPrice(resultSet.getDouble("foodPrice"));
                food.setBusinessId(resultSet.getInt("businessId"));
                food.setRemarks(resultSet.getString("remarks"));
            }
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }
        return food;
    }
}
