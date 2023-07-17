package com.elm.entity;

import lombok.Data;

@Data
public class DeliveryAddress {
    private Integer daId;
    private String contactName;
    private Integer contactSex;
    private String contactTel;
    private String address;
    private Integer userId;
}
