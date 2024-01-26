package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.request.*;
import com.mangojelly.backend.applicatoin.dto.response.GetAllGuestResponse;
import com.mangojelly.backend.applicatoin.dto.response.GetAllMovieResponse;
import com.mangojelly.backend.applicatoin.dto.response.GuestCreateResponse;
import com.mangojelly.backend.applicatoin.facade.MemberFacade;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import com.mangojelly.backend.global.security.support.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.API_SUCCESS_GUEST_CREATE, response));
    }

    @PutMapping("/guest/session")
    public ResponseEntity<ApiResponse<GetAllGuestResponse>> updateGuestSession(@RequestBody @Validated GuestSessionRequest request){
        GetAllGuestResponse response = memberFacade.updateSessionGuest(request.id(), request.session());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_GUEST_UPDATE, response));
    }

    @PutMapping("/guest/role")
    public ResponseEntity<ApiResponse<Void>> updateGuestRole(@RequestBody @Validated GuestRoleRequest request){
        memberFacade.updateRoleGuest(request.id(), request.role());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_GUEST_UPDATE));
    }

    @DeleteMapping("/guest/{guest_id}")
    public ResponseEntity<ApiResponse<Void>> deleteGuest(@PathVariable(name = "guest_id") int guest_id){
        memberFacade.deleteGuest(guest_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_GUEST_DELETE));
    }

}
