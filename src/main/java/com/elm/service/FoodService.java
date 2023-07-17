package com.elm.service;

import com.elm.entity.Food;

import java.util.List;

public interface FoodService {
    /**
     * 根据商家id查询商品列表
     *
     * @param businessId
     * @return
     */
    List<Food> queryFoodByBusiness(int businessId);

    /**
     * 查询食品列表
     *
     * @param foodIdList 食品id列表
     * @return 食品列表
     */
    List<Food> queryFoodList(List<Integer> foodIdList);

    /**
     * 根据食品id查询食品
     * @param foodId 食品id
     * @return 食品
     */
    Food queryFoodByFoodId(int foodId);
}
