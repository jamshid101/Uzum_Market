package com.example.uzum_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrderDTO {
    private String promocode;

    private Integer payment;

    private Integer address;

    private List<Integer> addOrderItemDTOS;
}
