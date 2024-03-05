package com.example.uzum_market.model;

import com.example.uzum_market.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


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

    private String colorName;

    @ManyToOne(optional = false)
    private Seller seller;

    private OrderStatus orderStatus;

    @ManyToOne(optional = false)
    private Orders order;

    private String specificationsType;

    private String specificationsName;

    private Integer attachmentId;
    @NotNull
    private Long createdAt;

    @ManyToOne(optional = false)
    private History history;

    @ManyToOne(optional = false)
    private Product product;
}
