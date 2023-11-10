package com.example.uzum_market.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.UUID;

public record ProductAddDTO(
        @NotBlank String name,
        @NotBlank String description,
        @Positive int pageCount,
        @PositiveOrZero double price,
        @NotNull UUID readSampleId,
        List<UUID> photoIds
) {
}
