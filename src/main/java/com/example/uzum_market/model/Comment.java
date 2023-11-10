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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private User user;

    @Column()
    private String  body;

    @ManyToOne(optional = false)
    private Comment comment;

    @ManyToOne(optional = false)
    private Product product;




}
