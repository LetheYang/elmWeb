package com.elm.service.impl;

import com.elm.dao.DeliveryAddressDao;
import com.elm.dao.impl.DeliveryAddressDaoImpl;
import com.elm.entity.DeliveryAddress;
import com.elm.service.DeliveryAddressService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 根据用户id查询地址列表
     *
     * @param userId 用户id
     * @return 地址列表
     */
    @Override
    public List<DeliveryAddress> queryAddressListByUserId(int userId) {
        try {
            return deliveryAddressDao.queryAddressListByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 根据地址id查询用户地址
     *
     * @param daId 地址id
     * @return 用户地址
     */
    @Override
    public DeliveryAddress queryAddressByDaId(int daId) {
        try {
            return deliveryAddressDao.queryAddressByDaId(daId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 用户添加地址
     *
     * @param deliveryAddress 地址
     * @return 添加结果
     */
    @Override
    public int addDeliveryAddress(DeliveryAddress deliveryAddress) {
        try {
            return deliveryAddressDao.addDeliveryAddress(deliveryAddress);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
