package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.GuestRequest;
import com.mangojelly.backend.applicatoin.dto.request.LoginRequest;
import com.mangojelly.backend.applicatoin.dto.request.SignupRequest;
import com.mangojelly.backend.applicatoin.dto.response.GuestCreateResponse;
import com.mangojelly.backend.domain.authToken.AuthTokenService;
import com.mangojelly.backend.domain.guest.GuestService;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomService;
import com.mangojelly.backend.global.security.support.TokenProvider;
import com.mangojelly.backend.global.security.support.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;
    private final AuthTokenService authTokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RoomService roomService;
    private final GuestService guestService;


    /**
     * 멤버를 등록하는 메소드
     * @param request
     */
    @Transactional
    public void saveMember(SignupRequest request) {
        memberService.save(request.email(), request.password(), request.nickname());
    }

    /**
     * 로그인 메소드
     * @param request
     * @return Token including Access, Refresh
     */
    @Transactional
    public TokenResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();
        log.info(authenticationToken.toString());
        Authentication authenticate = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);
        log.info(authenticate.toString());
        TokenResponse response = tokenProvider.generateTokenResponse(authenticate);

        authTokenService.save(Integer.parseInt(authenticate.getName()),response.refreshToken());
        return response;
    }

    /**
     * 게스트 생성 메서드
     * @param request
     * @return 게스트의 id
     */
    public GuestCreateResponse saveGuest(GuestRequest request){
        Room room = roomService.findByAddress(request.address());
        return GuestCreateResponse.of(guestService.save(request.nickName(), room).getId());
    }
}
