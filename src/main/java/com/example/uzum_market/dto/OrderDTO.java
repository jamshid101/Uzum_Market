package com.example.uzum_market.dto;

import com.example.uzum_market.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDTO(
        @NotNull Integer orderId,
        Double price,
        Integer addressId,
        Long date,
        String firstName,
        String lastName,
        String phoneNumber,
        Integer paymentId,
        Integer promoCodeId,
        OrderStatus orderStatus,
        List<OrderItemDTO> orderItemDTOList
) {
}
