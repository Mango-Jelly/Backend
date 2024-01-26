package com.mangojelly.backend.domain.authToken;

import org.springframework.stereotype.Component;

@Component
class AuthTokenMapper {
    AuthToken toEntity(int id, String refreshToken){
        return AuthToken.builder()
                .id(id)
                .value(refreshToken)
                .build();
    }
}
