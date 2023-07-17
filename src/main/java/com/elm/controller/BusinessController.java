package com.elm.controller;

import com.elm.entity.Business;
import com.elm.service.BusinessService;
import com.elm.service.impl.BusinessServiceImpl;
import com.elm.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author akemihomurasama
 */
public class BusinessController {
    BusinessService businessService = new BusinessServiceImpl();

    /**
     * 获取全部商家列表
     *
     * @return
     */
    public Result queryAllBusiness(HttpServletRequest request) {
        List<Business> businessList = businessService.queryAllBusiness();
        return Result.ok(businessList);
    }

    /**
     * 根据分类查询商家
     *
     * @param request
     * @return
     */
    public Result queryBusinessByOrderType(HttpServletRequest request) {
        String orderTypeId = request.getParameter("orderTypeId");
        if (orderTypeId == null) {
            return Result.fail("orderTypeId错误");
        }
        List<Business> businessList = businessService.queryBusinessByOrderType(Integer.parseInt(orderTypeId));
        return Result.ok(businessList);
    }

    /**
     * 根据商家id查询商家
     */
    public Result queryBusinessById(HttpServletRequest request) {
        String businessId = request.getParameter("businessId");
        if (businessId == null) {
            return Result.fail("businessId cannot be null");
        }
        Business business = businessService.queryBusinessById(Integer.parseInt(businessId));
        return Result.ok(business);
    }
}
