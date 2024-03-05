package com.example.uzum_market.model;

import com.example.uzum_market.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Double curPrice;

    @ManyToOne(optional = false)
    private Price price;

    @ManyToOne(optional = false)
    private Color  color;

    @ManyToOne
    private Orders order;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private Basket basket;


}
