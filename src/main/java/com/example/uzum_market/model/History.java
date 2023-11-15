package com.example.uzum_market.model;

import com.example.uzum_market.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(optional = false)
    private Seller seller;

    @ManyToOne(optional = false)
    private User user;

    private Long complateAt;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "history")
    private List<HistoryItem> storyItems;
}
