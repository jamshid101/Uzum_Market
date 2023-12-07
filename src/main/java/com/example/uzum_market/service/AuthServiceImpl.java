package com.example.uzum_market.service;

import com.example.uzum_market.config.jwtFilter.JWTProvider;
import com.example.uzum_market.dto.*;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.Balance;
import com.example.uzum_market.model.User;
import com.example.uzum_market.repository.AttachmentRepository;
import com.example.uzum_market.repository.UserRepository;
import com.example.uzum_market.utils.AppConstants;
import com.example.uzum_market.utils.EmailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AttachmentRepository attachmentRepository;

    public AuthServiceImpl(UserRepository userRepository,
                           @Lazy AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JWTProvider jwtProvider,
                           AttachmentRepository attachmentRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        User user1 = checkCredential(loginDTO.username(), loginDTO.password());
        return ApiResult.successResponse(generateTokenDTO(user1));
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        if (!accessToken.startsWith(AppConstants.BEARER_TYPE))
            throw RestException.restThrow("Bearer emas");

        accessToken = accessToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        refreshToken = refreshToken.substring(AppConstants.BEARER_TYPE.length()).trim();
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
        User user = userRepository.findByEmail(registerDTO.email()).orElseThrow(() -> RestException.restThrow("Iltimos email tasdiqlaganingizga ishonch hosil qiling", HttpStatus.BAD_REQUEST));

        if (!user.getCode().equals(registerDTO.code()))
            throw RestException.restThrow("activation kod xato ", HttpStatus.BAD_REQUEST);

        if (!registerDTO.password().equals(registerDTO.perPassword()))
            throw RestException.restThrow("parrollar birxilmas", HttpStatus.BAD_REQUEST);

        user.setName(registerDTO.name());
        user.setAccountNonLocked(true);
        user.setAttachment(attachmentRepository.findById(registerDTO.photo_id()).orElse(null));
        user.setPassword(passwordEncoder.encode(registerDTO.password()));

        userRepository.save(user);

        return ApiResult.successResponse("successfully");
    }

    @Override
    public ApiResult<Boolean> sendEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        String generationCode = EmailService.getGenerationCode();

        if (byEmail.isPresent()) {
            byEmail.get().setCode(generationCode);
            boolean b = EmailService.sendMessageToEmail(email, generationCode);

            if (!b) {
                throw RestException.restThrow("iltimos birozdan sung qayta urunib kuring", HttpStatus.BAD_REQUEST);
            }
            userRepository.save(byEmail.get());
            return ApiResult.successResponse(true);
        }

        boolean res = EmailService.sendMessageToEmail(email, generationCode);
        if (!res) {
            throw RestException.restThrow("iltimos birozdan sung qayta urunib kuring", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .balance(Balance.builder().amount(0.0).isActive(true).build())
                .email(email)
                .code(generationCode)
                .accountNonLocked(false)
                .password("1")
                .build();

        userRepository.save(user);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> forgotPassword(ResetDTO resetDTO) {
        Optional<User> user = userRepository.findByEmail(resetDTO.getEmail());

        if (user.isEmpty()) {
            throw RestException.restThrow("bunday user mavjud emas",HttpStatus.BAD_REQUEST);
        }

        User user1 = user.get();

        if (!user1.getCode().equals(resetDTO.getCode())) {
            throw RestException.restThrow("kiritilgan code xato",HttpStatus.BAD_REQUEST);
        }

        if (!user1.getPassword().equals(resetDTO.getPrePassword())){
            throw RestException.restThrow("kiritilgan password mos emas xato",HttpStatus.BAD_REQUEST);
        }

        user1.setPassword(resetDTO.getPassword());
        userRepository.save(user1);

        return ApiResult.successResponse(true);
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
