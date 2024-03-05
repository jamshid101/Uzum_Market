package com.example.uzum_market.model;

import com.example.uzum_market.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Promocode promocode;

    @ManyToOne(optional = false)
    private Payment payment;

    @Column(nullable = false)
    private Long createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @ManyToOne(optional = false)
    private Address  address;

    @ManyToOne(optional = false)
    private User  user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem>  orderItems;



}
