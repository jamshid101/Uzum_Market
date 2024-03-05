package com.example.uzum_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private Integer id;

    private Integer count;

    private Double price;

    private String color;

    private String specificationType;
    private String specificationName;

}
