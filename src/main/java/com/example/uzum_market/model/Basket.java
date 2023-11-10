package com.example.uzum_market.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    private Integer id;
    private List<User> user;
    private List<OrderItem> orderItems;


}
