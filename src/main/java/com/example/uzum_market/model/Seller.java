package com.example.uzum_market.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String passport;

    @OneToOne
    @JoinColumn(nullable = false)
    private Address address;

    @JoinColumn(nullable = false)
    @OneToOne
    private User user;


}
