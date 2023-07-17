package com.elm.service;

import com.elm.entity.Cart;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author akemihomurasama
 */
public interface CartService {
    /**
     * 查询当前用户在所有商家中的购物车的数量
     *
     * @param userId 用户id
     * @return 返回用户的购物车
     */
    Map<Integer, Integer> queryFoodCountInCart(String userId);

    /**
     * 查询一个商家中的购物车信息
     *
     * @param userId     用户id
     * @param businessId 商家id
     * @return 用户购物车内容
     */
    Map<Integer, Integer> queryFoodInBusiness(String userId, Integer businessId);

    /**
     * 向购物车添加商品，设置数量为1
     *
     * @param businessId 商家id
     * @param userId     用户id
     * @param foodId     食品id
     * @return 添加结果
     */
    int addToCart(int businessId, int userId, int foodId);

    /**
     * 增加购物车中商品的数量
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 返回增加结果
     */
    int increaseFoodInCart(int userId, int foodId);

    /**
     * 减少购物车中食品数量
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 修改结果
     */
    int decreaseFoodInCart(int userId, int foodId);

    /**
     * 从数据库中移除食品信息
     *
     * @param userId 用户id
     * @param foodId 食品id
     * @return 修改信息
     */
    int removeFoodFromCart(int userId, int foodId);
}