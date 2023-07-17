package com.elm.controller;

import com.elm.entity.DeliveryAddress;
import com.elm.service.DeliveryAddressService;
import com.elm.service.impl.DeliveryAddressServiceImpl;
import com.elm.utils.Result;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author akemihomurasama
 */
public class DeliveryController {
    DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();

    public Result queryAddressByUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        DeliveryAddress deliveryAddress = deliveryAddressService.queryAddressByUserId(NumberUtils.toInt(userId));
        return Result.ok(deliveryAddress);
    }
}
