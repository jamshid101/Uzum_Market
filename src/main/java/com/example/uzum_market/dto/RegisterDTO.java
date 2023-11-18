package com.example.uzum_market.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO (@NotBlank @Email String email, @NotBlank String password, @NotBlank String perPassword,
                           @NotBlank String name, @NotBlank String code, Integer photo_id){
}
