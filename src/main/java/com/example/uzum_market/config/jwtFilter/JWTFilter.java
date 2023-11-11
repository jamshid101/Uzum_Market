package com.example.uzum_market.config.jwtFilter;

import com.example.uzum_market.model.User;
import com.example.uzum_market.service.AuthServiceImpl;
import com.example.uzum_market.utils.AppConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final AuthServiceImpl authServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            if (authorization.startsWith(AppConstants.BEARER_TYPE))
                setAuthenticationBearer(request, authorization);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationBearer(HttpServletRequest request, String authorization) {
        String userId = jwtProvider.extractUserId(authorization.substring(AppConstants.BEARER_TYPE.length()).trim());

        Optional<User> optionalUser = authServiceImpl.findUserById(Integer.valueOf(userId));
        optionalUser.ifPresent(user -> {
            if (user.allOk())
                setAuthentication(request, user);
        });
    }

    private void setAuthentication(HttpServletRequest request, User user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

        //TODO sesion based + token based auth qilsak kerak bo'ladi
        WebAuthenticationDetails details = new WebAuthenticationDetails(request);
        authentication.setDetails(details);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
