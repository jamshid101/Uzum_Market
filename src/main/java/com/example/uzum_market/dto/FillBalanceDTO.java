package com.example.uzum_market.dto;

import lombok.Data;

@Data
public class FillBalanceDTO {
    private Double amount;

    private String cardNumber;

    private Integer paymentId;
}
