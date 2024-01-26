package com.mangojelly.backend.global.security.support;

import lombok.Builder;

public record TokenResponse(String accessToken, String refreshToken) {
    @Builder
    public TokenResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
