package com.example.uzum_market.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nameUz;

    @Column(nullable = false)
    private String nameRu;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Seller seller;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Boolean isStock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices;

}
