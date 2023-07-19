package com.elm.dto;

import com.elm.entity.Food;
import lombok.Data;

@Data
public class FoodDto extends Food {
    private int quantity;
}
