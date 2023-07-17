package com.elm.controller;

import com.elm.entity.Food;
import com.elm.service.FoodService;
import com.elm.service.impl.FoodServiceImpl;
import com.elm.utils.Result;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class FoodController {
    FoodService foodService = new FoodServiceImpl();

    /**
     * 查看商家的商品
     */
    public Result queryFoodByBusiness(HttpServletRequest request) {
        String businessId = request.getParameter("businessId");
        if (businessId == null) {
            return Result.fail("没有参数");
        }
        List<Food> foodList = foodService.queryFoodByBusiness(Integer.parseInt(businessId));
        return Result.ok(foodList);
    }

    /**
     * 根据食品id列表查询食品列表
     */
    public Result queryFoodList(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        List<Integer> foodIdList = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            foodIdList.add(Integer.valueOf(parameterNames.nextElement()));
        }
        List<Food> foodList = foodService.queryFoodList(foodIdList);
        return Result.ok(foodList);
    }

    public Result queryFoodByFoodId(HttpServletRequest request) {
        String foodId = request.getParameter("foodId");
        Food food = foodService.queryFoodByFoodId(NumberUtils.toInt(foodId));
        return Result.ok(food);
    }
}
