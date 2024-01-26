package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest (@NotBlank String email, @NotBlank String password, @NotBlank String nickname){
    public SignupRequest(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
