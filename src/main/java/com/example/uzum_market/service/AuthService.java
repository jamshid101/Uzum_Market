package com.example.uzum_market.service;

import com.example.uzum_market.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> login(LoginDTO loginDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken,String refreshToken);

    ApiResult<?> register(RegisterDTO registerDTO);

    ApiResult<Boolean> sendEmail(String email);

    ApiResult<Boolean> forgotPassword(ResetDTO resetDTO);

}
