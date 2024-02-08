package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.GuestRequest;
import com.mangojelly.backend.applicatoin.dto.request.LoginRequest;
import com.mangojelly.backend.applicatoin.dto.request.SignupRequest;
import com.mangojelly.backend.applicatoin.dto.response.GuestCreateResponse;
import com.mangojelly.backend.applicatoin.dto.response.GetAllGuestResponse;
import com.mangojelly.backend.domain.authToken.AuthTokenService;
import com.mangojelly.backend.domain.guest.GuestService;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.role.RoleService;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;
    private final AuthTokenService authTokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RoomService roomService;
    private final GuestService guestService;
    private final RoleService roleService;


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
    @Transactional
    public GuestCreateResponse saveGuest(GuestRequest request){
        Room room = roomService.findByAddress(request.address());
        return GuestCreateResponse.of(guestService.save(request.nickName(), room).getId());
    }

    /**
     * 게스트 세션 수정 메서드
     * @param guest_id
     * @param session
     * @return
     */
    @Transactional
    public GetAllGuestResponse updateSessionGuest(int guest_id, String session){
        return GetAllGuestResponse.of(guestService.updateSession(guest_id, session));
    }

    /**
     * 게스트 역할 추가 메서드
     * @param guest_id
     * @param role_id
     * @return
     */
    @Transactional
    public GuestCreateResponse updateRoleGuest(int guest_id, int role_id){
        Role role = roleService.findById(role_id);
        return GuestCreateResponse.of(guestService.updateRole(guest_id, role).getId());
    }

    /**
     * 게스트 삭제 메서드
     * @param guest_id
     */
    @Transactional
    public void deleteGuest(int guest_id){
        guestService.deleteGuestById(guest_id);
    }
}
