package com.elm.service.impl;

import com.elm.dao.FoodDao;
import com.elm.dao.impl.FoodDaoImpl;
import com.elm.entity.Food;
import com.elm.service.FoodService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao = new FoodDaoImpl();

    @Override
    public List<Food> queryFoodByBusiness(int businessId) {
        try {
            return foodDao.queryFoodByBusiness(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 查询食品列表
     *
     * @param foodIdList 食品id列表
     * @return 食品列表
     */
    @Override
    public List<Food> queryFoodList(List<Integer> foodIdList) {
        List<Food> foodList = new ArrayList<>();
        foodIdList.forEach(foodId -> {
            try {
                Food food;
                food = foodDao.queryFoodByFoodId(foodId);
                foodList.add(food);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtil.close();
            }
        });
        return foodList;
    }

    /**
     * 根据食品id查询食品
     *
     * @param foodId 食品id
     * @return 食品
     */
    @Override
    public Food queryFoodByFoodId(int foodId) {
        try {
            return foodDao.queryFoodByFoodId(foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
