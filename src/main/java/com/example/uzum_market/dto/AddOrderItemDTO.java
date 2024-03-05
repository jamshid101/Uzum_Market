package com.example.uzum_market.dto;

import com.example.uzum_market.model.Basket;
import com.example.uzum_market.model.Color;
import com.example.uzum_market.model.Orders;
import com.example.uzum_market.model.Price;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrderItemDTO {

    private Integer id;

    @Column(nullable = false)
    private Integer count;

    @ManyToOne(optional = false)
    private Price price;

    @ManyToOne(optional = false)
    private Color color;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private Basket basket;
}
