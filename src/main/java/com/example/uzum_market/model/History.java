package com.example.uzum_market.model;

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
    private String status;

    @ManyToOne(optional = false)
    private Seller seller;

    @ManyToOne(optional = false)
    private User user;

    private Long complateAt;

    @OneToMany(mappedBy = "history")
    private List<HistoryItem> storyItems;
}
