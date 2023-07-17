package com.elm.service.impl;

import com.elm.dao.OrderDetailetDao;
import com.elm.dao.impl.OrderDetailetDaoImpl;
import com.elm.entity.OrderDetailet;
import com.elm.service.OrderDetailetService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailetServiceImpl implements OrderDetailetService {
    OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

    /**
     * 根据订单id 查询订单明细
     *
     * @param orderId 订单ID
     * @return 订单明细
     */
    @Override
    public List<OrderDetailet> queryOrderDetaByOrderId(int orderId) {
        try {
            return orderDetailetDao.queryOrderDetaByOrderId(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
