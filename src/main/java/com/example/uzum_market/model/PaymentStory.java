package com.example.uzum_market.model;

import com.example.uzum_market.enums.TransferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(optional = false)
    private User sender;

    @ManyToOne(optional = false)
    private User receiver;

    @Column
    @Positive
    private double amount;

    @Column(nullable = false)
    private LocalDateTime localDateTime;


    @Enumerated(EnumType.STRING)
    private TransferStatus status;






}
