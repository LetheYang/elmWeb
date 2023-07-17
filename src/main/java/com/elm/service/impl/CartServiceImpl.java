package com.elm.service.impl;

import com.elm.dao.CartDao;
import com.elm.dao.impl.CartDaoImpl;
import com.elm.service.CartService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author akemihomurasama
 */
public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();

    /**
     * 查询当前用户在所有商家中的购物车的数量
     *
     * @param userId 用户id
     * @return 返回用户的购物车
     */
    @Override
    public Map<Integer, Integer> queryFoodCountInCart(String userId) {
        Map<Integer, Integer> map = null;
        try {
            map = cartDao.queryFoodCountInCart(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close();
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
    public Map<Integer, Integer> queryFoodInBusiness(String userId, Integer businessId) {
        try {
            return cartDao.queryFoodInBusiness(userId, businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 向购物车添加商品，设置数量为1
     *
     * @param userId     用户id
     * @param foodId     食品id
     * @param businessId 商家id
     * @return 添加结果
     */
    @Override
    public int addToCart(int businessId, int userId, int foodId) {
        try {
            return cartDao.addToCart(businessId, userId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
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
    public int increaseFoodInCart(int userId, int foodId) {
        try {
            return cartDao.increaseFoodInCart(userId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
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
    public int decreaseFoodInCart(int userId, int foodId){
        try {
            return cartDao.decreaseFoodInCart(userId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
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
    public int removeFoodFromCart(int userId, int foodId){
        try {
            return cartDao.removeFoodFromCart(userId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
