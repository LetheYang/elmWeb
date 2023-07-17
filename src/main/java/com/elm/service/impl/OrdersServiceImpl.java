package com.elm.service.impl;

import com.elm.dao.CartDao;
import com.elm.dao.OrderDetailetDao;
import com.elm.dao.OrdersDao;
import com.elm.dao.impl.CartDaoImpl;
import com.elm.dao.impl.OrderDetailetDaoImpl;
import com.elm.dao.impl.OrdersDaoImpl;
import com.elm.entity.OrderDetailet;
import com.elm.entity.Orders;
import com.elm.service.OrdersService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao = new OrdersDaoImpl();
    OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    /**
     * 查询当前用户的所有订单
     *
     * @param userId 用户id
     * @return 用户所有订单
     */
    @Override
    public List<Orders> queryOrdersById(int userId) {
        try {
            return ordersDao.queryOrdersById(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 用户结算
     *
     * @param orders            用户订单明细
     * @param orderDetailetList 用户订单中食品明细
     * @return 添加结果
     */
    @Override
    public int confirmOrder(Orders orders, List<OrderDetailet> orderDetailetList) {
        try {
            orders.setOrderState(0);
            JDBCUtil.beginTransaction();
            int orderId = ordersDao.addOrder(orders);
            //设置食品订单中每个食品所属的订单编号
            orderDetailetList.forEach(orderDetailet -> orderDetailet.setOrderId(orderId));
            for (OrderDetailet orderDetailet : orderDetailetList) {
                orderDetailetDao.addToOrderDetailet(orderDetailet);
                //清空购物车
                cartDao.removeFoodFromCart(orders.getUserId(), orderDetailet.getFoodId());
            }
            JDBCUtil.commitTransaction();
            return orderId;
        } catch (SQLException e) {
            JDBCUtil.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 根据订单id查询订单
     *
     * @param orderId 订单id
     * @return 订单信息
     */
    @Override
    public Orders queryOrderByOrderId(int orderId) {
        try {
            return ordersDao.queryOrderByOrderId(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 用户付款后设置订单状态为已付款
     *
     * @param orderId 订单id
     * @return 返回更新结果
     */
    @Override
    public int upDateOrderStatus(int orderId) {
        try {
            return ordersDao.upDateOrderStatus(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
