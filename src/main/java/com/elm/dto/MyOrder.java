package com.elm.dto;

import com.elm.entity.Food;
import com.elm.entity.Orders;
import lombok.Data;

import java.util.List;
@Data
public class MyOrder extends Orders {
    private String businessName;

    private List<FoodDto> foodDtoList;
}
