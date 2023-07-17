package com.elm.dao;

import com.elm.entity.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {
    /**
     * 根据商家id查询商品列表
     *
     * @param businessId 商品id
     * @return 食品类表
     */
    List<Food> queryFoodByBusiness(int businessId) throws SQLException;

    /**
     * 根据食品id查询食品信息
     *
     * @param foodId 食品id
     * @return 食品
     */
    Food queryFoodByFoodId(int foodId) throws SQLException;
}
