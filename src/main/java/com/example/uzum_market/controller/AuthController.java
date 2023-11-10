package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController(AuthController.BASE_PATH)
public interface AuthController {
    String BASE_PATH = "/api/auth";
    String LOGIN_PATH = "/login";
    String REFRESH_TOKEN_PATH = "/refresh-token";
    String CONFIRM_FOR_SELLER_PATH = "/confirm-for-admin";
    String REGISTER_PATH = "/register";
    String FORGOT_PATH = "/forgot-password";

    @PostMapping(LOGIN_PATH)
    HttpEntity<ApiResult<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO);

    @PatchMapping(REFRESH_TOKEN_PATH)
    HttpEntity<ApiResult<TokenDTO>> refreshToken(
            @RequestHeader(AppConstants.AUTH_HEADER) String accessToken,
            @RequestHeader(AppConstants.REFRESH_AUTH_HEADER) String refreshToken
    );

    @PostMapping(CONFIRM_FOR_SELLER_PATH)
    HttpEntity<ApiResult<TokenDTO>> confirmForAdmin(@Valid @RequestBody ConfirmAdminDTO confirmAdminDTO);
    @PostMapping(REGISTER_PATH)
    HttpEntity<ApiResult<TokenDTO>> register(@Valid @RequestBody RegisterDTO registerDTO);
    @PostMapping(FORGOT_PATH)
    HttpEntity<ApiResult<Boolean>> forgotPassword(@RequestParam String email);

    @PostMapping(FORGOT_PATH)
    HttpEntity<ApiResult<Boolean>> reset(@RequestParam String email);
    //todo reset password
}