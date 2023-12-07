package com.example.uzum_market.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record PriceAddDTO(
        @NotBlank String specificationName,
        @NotBlank ColorAddDTO color,
        @Positive Double price,
        @Positive int count


) {
}
