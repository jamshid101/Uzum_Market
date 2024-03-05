package com.example.uzum_market.model;

import com.example.uzum_market.enums.TransferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Column
    @Positive
    private double amount;

    private String cardNumber;

    @ManyToOne
    private Payment payment;

    @Column(nullable = false)
    private Long createAt;


    @Enumerated(EnumType.STRING)
    private TransferStatus status;


}
