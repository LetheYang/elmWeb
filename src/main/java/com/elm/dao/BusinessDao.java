package com.elm.dao;

import com.elm.entity.Business;
import com.elm.entity.Food;

import java.sql.SQLException;
import java.util.List;

public interface BusinessDao {
    /**
     * 查询全部商家
     * @return
     * @throws SQLException
     */
    List<Business> queryAllBusiness() throws SQLException;

    /**
     * 根据类别查询商家
     * @param orderTypeId
     * @return
     * @throws SQLException
     */
    List<Business> queryBusinessByType(int orderTypeId) throws SQLException;

    /**
     * 根据商家id查询商家信息
     * @param businessId
     * @return
     * @throws SQLException
     */
    Business queryBusinessById(int businessId) throws SQLException;
}
