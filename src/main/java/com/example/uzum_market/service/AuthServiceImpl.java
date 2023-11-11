package com.example.uzum_market.service;

import com.example.uzum_market.config.jwtFilter.JWTProvider;
import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.LoginDTO;
import com.example.uzum_market.dto.RegisterDTO;
import com.example.uzum_market.dto.TokenDTO;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.User;
import com.example.uzum_market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.username())
                .orElseThrow(() -> RestException.restThrow("User not found", HttpStatus.BAD_REQUEST));
        return ApiResult.successResponse(generateTokenDTO(user));
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        if (!accessToken.startsWith(AppConstants.BEARER_TYPE))
            throw RestException.restThrow("Bearer emas");

        accessToken = accessToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        if (!jwtProvider.isExpired(accessToken))
            throw RestException.restThrow("Token muddati tugamagan");

        if (!jwtProvider.validRefreshToken(refreshToken))
            throw RestException.restThrow("Refresh token valid emas");

        String userId = jwtProvider.extractUserIdFromRefreshToken(refreshToken);
        User user = findUserById(Integer.valueOf(userId))
                .orElseThrow(() -> RestException.restThrow("User not found: " + userId, HttpStatus.NOT_FOUND));

        TokenDTO tokenDTO = generateTokenDTO(user);

        return ApiResult.successResponse(tokenDTO);
    }

    @Override
    public ApiResult<?> register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public ApiResult<Boolean> sendEmail(String email) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<User> findUserById(Integer userId) {
        if (userId == null)
            return Optional.empty();
        return userRepository.findById(userId);
    }


    private TokenDTO generateTokenDTO(User user) {
        String id = user.getId().toString();
        String accessToken = jwtProvider.createAccessToken(id);
        String refreshToken = jwtProvider.createRefreshAccessToken(id);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public User checkCredential(String username, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return (User) authentication.getPrincipal();
    }
}
