package com.elm.controller;

import com.elm.entity.Cart;
import com.elm.service.CartService;
import com.elm.service.impl.CartServiceImpl;
import com.elm.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
public class CartController {
    CartService cartService = new CartServiceImpl();

    public Result queryFoodCountInCart(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        if (userId == null) {
            return Result.fail("userId is empty");
        }
        Map<Integer, Integer> map = cartService.queryFoodCountInCart(userId);
        return Result.ok(map);
    }

    public Result queryFoodInBusiness(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String businessId = request.getParameter("businessId");
        Map<Integer, Integer> map = cartService.queryFoodInBusiness(userId, NumberUtils.toInt(businessId));
        return Result.ok(map);
    }

    public Result increaseFoodInCart(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String foodId = request.getParameter("foodId");
        cartService.increaseFoodInCart(NumberUtils.toInt(userId), NumberUtils.toInt(foodId));
        return Result.ok();
    }

    public Result addToCart(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String foodId = request.getParameter("foodId");
        String businessId = request.getParameter("businessId");
        cartService.addToCart(NumberUtils.toInt(businessId), NumberUtils.toInt(userId), NumberUtils.toInt(foodId));
        return Result.ok();
    }

    public Result decreaseFoodInCart(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String foodId = request.getParameter("foodId");
        cartService.decreaseFoodInCart(NumberUtils.toInt(userId), NumberUtils.toInt(foodId));
        return Result.ok();
    }

    public Result removeFoodFromCart(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String foodId = request.getParameter("foodId");
        cartService.removeFoodFromCart(NumberUtils.toInt(userId), NumberUtils.toInt(foodId));
        return Result.ok();
    }

}
