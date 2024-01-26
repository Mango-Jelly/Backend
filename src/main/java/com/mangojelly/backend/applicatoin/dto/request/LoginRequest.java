package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequest(@NotBlank String email, @NotBlank String password) {

    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
