package com.elm.controller;

import com.elm.entity.OrderType;
import com.elm.service.OrderTypeService;
import com.elm.service.impl.OrderTypeServiceImpl;
import com.elm.utils.Result;

import javax.servlet.http.*;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class OrderTypeController extends HttpServlet {
    OrderTypeService orderTypeService = new OrderTypeServiceImpl();

    /**
     * 获取商家类型列表
     * @return
     */
    public Result queryAllOrderType(HttpServletRequest request){
        List<OrderType> allOrderTypes = orderTypeService.findAllOrderTypes();
        return Result.ok(allOrderTypes);
    }
}
