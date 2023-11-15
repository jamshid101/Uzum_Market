package com.example.uzum_market.dto;

import com.example.uzum_market.model.History;
import com.example.uzum_market.model.Orders;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderHistoryDTO {

    @NotBlank
    private String orderStatus;
    @NotNull
    private Long cretedAt;

    private List<HistoryDTO> historyList;

}
