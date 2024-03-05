package com.example.uzum_market.dto;

import com.example.uzum_market.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDTO {
    private Integer orderItemId;
    private OrderStatus orderStatus;
}
