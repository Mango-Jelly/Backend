package com.mangojelly.backend.global.config;

import com.mangojelly.backend.global.security.support.JwtAccessDeniedHandler;
import com.mangojelly.backend.global.security.support.JwtAuthenticationEntryPoint;
import com.mangojelly.backend.global.security.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable
            ).headers(headerConfig ->
                headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                )
            ).authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/api/login").permitAll()
                    .anyRequest().authenticated()
            ).exceptionHandling(exceptionConfig->
                exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)
                );
        return http.build();
    }
}
