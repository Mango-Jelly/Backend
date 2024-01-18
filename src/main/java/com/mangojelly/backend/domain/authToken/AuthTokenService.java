package com.mangojelly.backend.domain.authToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;
    private final AuthTokenMapper authTokenMapper;

    public AuthToken save(int memberId, String refreshToken) {
        return authTokenRepository.save(authTokenMapper.toEntity(memberId,refreshToken));
    }
}
