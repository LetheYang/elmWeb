package com.elm.entity;

import lombok.Data;

@Data
public class Orders {
    private Integer orderId;
    private Integer userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId;
    private Integer orderState;
}
