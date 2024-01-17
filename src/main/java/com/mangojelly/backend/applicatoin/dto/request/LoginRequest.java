package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequest(@NotBlank String email, @NotBlank String password) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
