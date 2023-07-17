package com.elm.service.impl;

import com.elm.dao.BusinessDao;
import com.elm.dao.impl.BusinessDaoImpl;
import com.elm.entity.Business;
import com.elm.service.BusinessService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class BusinessServiceImpl implements BusinessService {
    BusinessDao businessDao = new BusinessDaoImpl();

    @Override
    public List<Business> queryAllBusiness() {
        try {
            return businessDao.queryAllBusiness();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    @Override
    public List<Business> queryBusinessByOrderType(int orderTypeId) {
        try {
            return businessDao.queryBusinessByType(orderTypeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 根据商家id查询信息
     *
     * @param businessId 商家id
     * @return
     */
    @Override
    public Business queryBusinessById(int businessId) {
        try {
            return businessDao.queryBusinessById(businessId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }
}
