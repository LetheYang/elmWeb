package com.elm.service.impl;

import com.elm.dao.OrderTypeDao;
import com.elm.dao.impl.OrderTypeDaoImpl;
import com.elm.entity.OrderType;
import com.elm.service.OrderTypeService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class OrderTypeServiceImpl implements OrderTypeService {
    OrderTypeDao orderTypeDao = new OrderTypeDaoImpl();

    @Override
    public List<OrderType> findAllOrderTypes() {
        try {
            return orderTypeDao.findAllorderType();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
