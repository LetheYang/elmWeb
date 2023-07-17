package com.elm.service;

import com.elm.entity.Business;

import java.util.List;

public interface BusinessService {
    /**
     * 查询全部商家
     *
     * @return
     */
    List<Business> queryAllBusiness();

    /**
     * 根据类别查询商家
     *
     * @param orderTypeId
     * @return
     */
    List<Business> queryBusinessByOrderType(int orderTypeId);

    /**
     * 根据商家
     * @param businessId
     * @return
     */
    Business queryBusinessById(int businessId);
}
