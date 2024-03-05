package com.example.uzum_market.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddBasketDTO {
    private Integer productId;
    private Integer priceId;
    private @Positive Integer count;

}
