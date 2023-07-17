package com.elm.service.impl;

import com.elm.dao.DeliveryAddressDao;
import com.elm.dao.impl.DeliveryAddressDaoImpl;
import com.elm.entity.DeliveryAddress;
import com.elm.service.DeliveryAddressService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();

    /**
     * 根据用户id查询用户地址
     *
     * @param userId 用户id
     * @return 用户地址
     */
    @Override
    public DeliveryAddress queryAddressByUserId(int userId) {
        try {
            return deliveryAddressDao.queryAddressByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
