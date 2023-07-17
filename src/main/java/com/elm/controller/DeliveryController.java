package com.elm.controller;

import com.elm.entity.DeliveryAddress;
import com.elm.service.DeliveryAddressService;
import com.elm.service.impl.DeliveryAddressServiceImpl;
import com.elm.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class DeliveryController {
    DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
    ObjectMapper objectMapper = new ObjectMapper();

    public Result queryAddressByUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        DeliveryAddress deliveryAddress = deliveryAddressService.queryAddressByUserId(NumberUtils.toInt(userId));
        return Result.ok(deliveryAddress);
    }

    public Result queryAddressListByUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        List<DeliveryAddress> deliveryAddressList = deliveryAddressService.queryAddressListByUserId(NumberUtils.toInt(userId));
        return Result.ok(deliveryAddressList);
    }

    public Result queryAddressByDaId(HttpServletRequest request) {
        String daId = request.getParameter("daId");
        DeliveryAddress deliveryAddress = deliveryAddressService.queryAddressByDaId(NumberUtils.toInt(daId));
        return Result.ok(deliveryAddress);
    }

    public Result addAddress(HttpServletRequest request) {
        String userAddress = request.getParameter("userAddress");
        DeliveryAddress deliveryAddress;
        try {
            deliveryAddress = objectMapper.readValue(userAddress, DeliveryAddress.class);
        } catch (IOException e) {
            return Result.fail("添加失败");
        }
        deliveryAddressService.addDeliveryAddress(deliveryAddress);
        return Result.ok();
    }
}
