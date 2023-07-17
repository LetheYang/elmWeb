package com.elm.entity;

import lombok.Data;

@Data
public class OrderDetailet {
    private Integer odId;
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;
}
