package com.example.uzum_market.dto;

import lombok.Data;

import java.util.List;

@Data
public class BasketDTO {
    private Double totalPrice;
    private Integer totalCountOfProduct;
    private List<ProductForBasketDTO> productDTOS;
}
