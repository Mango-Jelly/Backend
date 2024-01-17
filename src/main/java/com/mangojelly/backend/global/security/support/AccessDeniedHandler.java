package com.mangojelly.backend.global.security.support;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.security.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    private final HandlerExceptionResolver resolver;

    public AccessDeniedHandler(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) {
        resolver.resolveException(
                request,
                response,
                null,
                new AuthException(
                        ErrorCode.ERROR_CLIENT_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE));
    }
}
