package com.elm.dao;

import com.elm.entity.DeliveryAddress;

import java.sql.SQLException;

/**
 * @author akemihomurasama
 */
public interface DeliveryAddressDao {
    /**
     * 根据用户id查询用户地址
     * @param userId 用户id
     * @return 用户地址
     */
    DeliveryAddress queryAddressByUserId(int userId) throws SQLException;
}
