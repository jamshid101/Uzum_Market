package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.User;
import com.example.uzum_market.repository.UserRepository;
import com.example.uzum_market.service.AuthService;
import com.example.uzum_market.utils.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public HttpEntity<ApiResult<TokenDTO>> login(LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.login(loginDTO));
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> refreshToken(String accessToken, String refreshToken) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.refreshToken(accessToken,refreshToken));
    }

    @Override
    public HttpEntity<ApiResult<TokenDTO>> confirmForAdmin(ConfirmAdminDTO confirmAdminDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<?>> register(RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> sendEmail(String email) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.sendEmail(email));
    }

    @Override
    public HttpEntity<ApiResult<Boolean>> reset(ResetDTO resetDTO) {
        return ResponseEntity.ok(authService.forgotPassword(resetDTO));
    }
}
