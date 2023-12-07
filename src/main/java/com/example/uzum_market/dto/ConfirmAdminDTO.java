package com.example.uzum_market.dto;

import com.example.uzum_market.utils.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.*;

public record ConfirmAdminDTO(@NotNull UUID id,
                              @NotBlank @Pattern(regexp = AppConstants.PASSWORD_REGEXP) String password,
                              @NotBlank @Pattern(regexp = AppConstants.PASSWORD_REGEXP) String prePassword,
                              @NotBlank String name) {

}
