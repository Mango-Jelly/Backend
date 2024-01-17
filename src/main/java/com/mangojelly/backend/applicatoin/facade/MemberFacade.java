package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.LoginRequest;
import com.mangojelly.backend.applicatoin.dto.request.SignupRequest;
import com.mangojelly.backend.domain.authToken.AuthTokenService;
import com.mangojelly.backend.global.security.support.TokenResponse;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import com.mangojelly.backend.global.security.support.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;
    private final AuthTokenService authTokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    /**
     * 멤버를 등록하는 메소드
     * @param request
     */
    @Transactional
    public void saveMember(SignupRequest request) {
        if(memberService.findByEmailWithOptional(request.email()).isPresent())
            throw BusinessException.of(ErrorCode.API_ERROR_MEMBER_ALREADY_EXISTED);
        memberService.save(request.email(), request.password(), request.nickname());
    }

    public TokenResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authenticate = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        TokenResponse response = tokenProvider.generateTokenResponse(authenticate);

        authTokenService.save(Integer.parseInt(authenticate.getName()),response.refreshToken());
        return response;
    }
}
