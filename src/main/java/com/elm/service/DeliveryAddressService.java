package com.elm.service;

import com.elm.entity.DeliveryAddress;

/**
 * @author akemihomurasama
 */
public interface DeliveryAddressService {
    /**
     * 根据用户id查询用户地址
     * @param userId 用户id
     * @return 用户地址
     */
    DeliveryAddress queryAddressByUserId(int userId);
}
