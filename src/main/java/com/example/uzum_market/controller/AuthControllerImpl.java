package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import org.springframework.http.HttpEntity;

public class AuthControllerImpl implements AuthController {
    @Override
    public HttpEntity<ApiResult<TokenDTO>> login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> refreshToken(String accessToken, String refreshToken) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> confirmForAdmin(ConfirmAdminDTO confirmAdminDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> sendEmail(String email) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> reset(String email) {
        return null;
    }
}
