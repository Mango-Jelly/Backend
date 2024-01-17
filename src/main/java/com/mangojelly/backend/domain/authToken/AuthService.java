package com.mangojelly.backend.domain.authToken;

import com.mangojelly.backend.applicatoin.dto.response.LoginResponse;
import com.mangojelly.backend.global.security.dto.request.LoginRequest;
import com.mangojelly.backend.global.security.dto.response.TokenResponse;
import com.mangojelly.backend.global.security.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthTokenRepository authTokenRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse login(String email) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, "");
        Authentication authenticate =
                authenticationManagerBuilder.getObject().authenticate(authentication);

        TokenResponse response = tokenProvider.generateTokenResponse(authenticate);
        int memberId = Integer.parseInt(authenticate.getName());

        AuthToken token = AuthToken.builder().id(memberId).value(response.getRefreshToken()).build();
        authTokenRepository.save(token);
        return response;
    }
}
