package com.example.uzum_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetDTO {
    private String email;
    private String code;
    private String password;
    private String prePassword;

}
