package com.elm.controller;

import com.elm.entity.OrderDetailet;
import com.elm.service.OrderDetailetService;
import com.elm.service.impl.OrderDetailetServiceImpl;
import com.elm.utils.Result;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderDetailetController {
    OrderDetailetService orderDetailetService = new OrderDetailetServiceImpl();

    public Result queryOrderDetaByOrderId(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        List<OrderDetailet> orderDetailetList = orderDetailetService.queryOrderDetaByOrderId(NumberUtils.toInt(orderId));
        return Result.ok(orderDetailetList);
    }
}
