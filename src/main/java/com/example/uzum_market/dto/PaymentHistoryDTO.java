package com.example.uzum_market.dto;

import com.example.uzum_market.enums.TransferStatus;
import com.example.uzum_market.model.Payment;
import com.example.uzum_market.model.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PaymentHistoryDTO {

    private User sender;

    private User receiver;

    private double amount;

    private String cardNumber;

    private Payment payment;
    private LocalDateTime localDateTime;

    private TransferStatus status;
}
