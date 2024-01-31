package com.mangojelly.backend.global.security.support;

import lombok.Builder;

public record TokenResponse(String accessToken, String refreshToken, String nickName) {
    @Builder
    public TokenResponse(String accessToken, String refreshToken, String nickName){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickName = nickName;
    }
}
