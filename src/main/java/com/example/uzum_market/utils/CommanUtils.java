package com.example.uzum_market.utils;

import com.example.uzum_market.controller.AttachmentController;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@UtilityClass
public class CommanUtils {

    public static User getCurrentUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
            throw RestException.restThrow("OKa yopiq yul", HttpStatus.UNAUTHORIZED);

        return (User) authentication.getPrincipal();
    }

    public static String makeFileUrl(Integer id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path(AttachmentController.BASE_PATH)
                .path(id.toString()).toUriString();
    }
}
