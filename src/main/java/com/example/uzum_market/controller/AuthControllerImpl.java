package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.repository.UserRepository;
import com.example.uzum_market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

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
    public HttpEntity<ApiResult<?>> register(RegisterDTO registerDTO) {
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
