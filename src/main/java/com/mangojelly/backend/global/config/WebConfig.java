package com.mangojelly.backend.global.config;

import com.mangojelly.backend.global.security.annotation.AuthenticatedArgumentResolver;
import com.mangojelly.backend.global.security.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WebConfig implements WebMvcConfigurer {
    private final TokenProvider tokenProvider;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedArgumentResolver(tokenProvider));
    }
}
