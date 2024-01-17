package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest (@NotBlank String email, @NotBlank String password, @NotBlank String nickname){
}
