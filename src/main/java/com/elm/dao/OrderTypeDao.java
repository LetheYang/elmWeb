package com.elm.dao;

import com.elm.entity.OrderType;

import java.sql.SQLException;
import java.util.List;

public interface OrderTypeDao {
    List<OrderType> findAllorderType() throws SQLException;
}
