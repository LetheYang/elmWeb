package com.elm.entity;

import lombok.Data;

/**
 * @author akemihomurasama
 */
@Data
public class Business {
    private Integer businessId;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private String businessImg;
    private Integer orderTypeId;
    private Double starPrice;
    private Double deliveryPrice;
    private String remarks;
}
