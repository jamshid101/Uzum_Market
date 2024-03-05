package com.example.uzum_market.dto;

import com.example.uzum_market.enums.OrderStatus;
import lombok.Data;

@Data
public class MainCriteriaForOrder {
    private int page = 0;

    private int size = 20;

    private OrderStatus orderStatus;
}
