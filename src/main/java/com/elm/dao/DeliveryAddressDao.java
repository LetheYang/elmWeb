package com.elm.dao;

import com.elm.entity.DeliveryAddress;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public interface DeliveryAddressDao {
    /**
     * 根据用户id查询用户地址
     *
     * @param userId 用户id
     * @return 用户地址
     */
    DeliveryAddress queryAddressByUserId(int userId) throws SQLException;

    /**
     * 根据用户id查询地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    List<DeliveryAddress> queryAddressListByUserId(int userId) throws SQLException;

    /**
     * 根据地址id查询地址
     * @param daId 地址id
     * @return 用户地址
     */
    DeliveryAddress queryAddressByDaId(int daId) throws SQLException;

    /**
     * 用户添加地址
     * @param deliveryAddress 地址
     * @return 添加结果
     */
    int addDeliveryAddress(DeliveryAddress deliveryAddress) throws SQLException;
}
