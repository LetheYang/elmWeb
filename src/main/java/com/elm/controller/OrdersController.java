package com.elm.controller;

import com.elm.entity.OrderDetailet;
import com.elm.entity.Orders;
import com.elm.service.OrdersService;
import com.elm.service.impl.OrdersServiceImpl;
import com.elm.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersController {
    OrdersService ordersService = new OrdersServiceImpl();

    public Result queryOrdersById(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        if (userId == null) {
            return Result.fail("没有参数");
        }
        List<Orders> ordersList = ordersService.queryOrdersById(Integer.parseInt(userId));
        return Result.ok(ordersList);
    }

    /**
     * 用户确认订单功能
     *
     * @param request
     * @return
     */
    public Result confirmOrders(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Orders orders = objectMapper.readValue(request.getParameter("orders"), Orders.class);
            String userCartInfoJsonStr = request.getParameter("userCartInfo");
            List<OrderDetailet> orderDetailetList = objectMapper
                    .readValue(userCartInfoJsonStr,
                            objectMapper.getTypeFactory()
                                    .constructParametricType(List.class, OrderDetailet.class));
            return Result.ok(ordersService.confirmOrder(orders, orderDetailetList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Result queryOrderById(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        Orders order = ordersService.queryOrderByOrderId(NumberUtils.toInt(orderId));
        return Result.ok(order);
    }

    public Result updateOrderStatus(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        ordersService.upDateOrderStatus(NumberUtils.toInt(orderId));
        return Result.ok();
    }
}
