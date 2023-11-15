package com.example.uzum_market.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDTO(@NotNull Integer productId,
                       Integer priceId,
                       Integer addressId,
                       String firstName,
                       String lastName,
                       String phoneNumber,
                       Integer paymentId
) {
}
