package com.mangojelly.backend.global.security.annotation;

import com.mangojelly.backend.global.security.support.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuthenticatedArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_TYPE = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticated.class);
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = Objects.requireNonNull(request)
                .getHeader(HEADER_AUTHORIZATION);
        String token = authorization.substring(BEARER_TYPE.length()).trim();
        return Math.toIntExact(tokenProvider.getPayload(token));
    }
}