package com.mangojelly.backend.global.security.support;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.security.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    public AuthenticationEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) {
        resolver.resolveException(
                request,
                response,
                null,
                new AuthException(ErrorCode.ERROR_CLIENT_BY_AUTHORIZATION_IS_NECESSARY));
    }
}
