package com.example.uzum_market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer count;

    @NotNull
    private Double price;

    @NotBlank
    private String colorName;

    @NotBlank
    private String orderStatus;

    @ManyToOne(optional = false)
    private Orders order;

    @NotBlank
    private String SpecificationsType;

    @NotBlank
    private String SpecificationsName;

    @NotNull
    private Long cretedAt;

    @ManyToOne(optional = false)
    private History history;
}
