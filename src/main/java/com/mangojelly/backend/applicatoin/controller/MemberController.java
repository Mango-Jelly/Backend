package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.request.GuestRequest;
import com.mangojelly.backend.applicatoin.dto.request.LoginRequest;
import com.mangojelly.backend.applicatoin.dto.request.SignupRequest;
import com.mangojelly.backend.applicatoin.dto.response.GuestCreateResponse;
import com.mangojelly.backend.applicatoin.facade.MemberFacade;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import com.mangojelly.backend.global.security.support.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberFacade memberFacade;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@RequestBody @Validated SignupRequest request){
        memberFacade.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SAVE));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody @Validated LoginRequest request){
        TokenResponse response = memberFacade.login(request);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_LOGIN,response));
    }

    @PostMapping("/guest")
    public ResponseEntity<ApiResponse<GuestCreateResponse>> createGuest(@RequestBody @Validated GuestRequest request){
        GuestCreateResponse response = memberFacade.saveGuest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.API_SUCCESS_GUEST_CREATE));
    }
}
