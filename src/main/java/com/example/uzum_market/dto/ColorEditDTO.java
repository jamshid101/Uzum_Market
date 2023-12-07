package com.example.uzum_market.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ColorEditDTO(
        List<Integer> photoList,
        @NotBlank String name

){
}
