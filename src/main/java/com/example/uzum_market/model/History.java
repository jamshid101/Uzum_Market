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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(optional = false)
    private User user;

    private Long complateAt;

    private Long createAt;

    @ManyToOne(optional = false)
    private Address address;

    @ManyToOne(optional = false)
    private Payment payment;

    @ManyToOne
    private Promocode promocode;

    @OneToMany(mappedBy = "history")
    private List<HistoryItem> storyItems;
}
