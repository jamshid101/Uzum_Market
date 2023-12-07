package com.example.uzum_market.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class HistoryDTO {
    @NotNull
    private Integer count;

    @NotNull
    private Double price;

    private String colorName;

    private String SpecificationsType;

    private String SpecificationsName;

    private String attachmentUri;
    @NotNull
    private Long createdAt;

}
