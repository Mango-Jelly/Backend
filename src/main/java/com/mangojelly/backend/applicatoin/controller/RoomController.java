package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.facade.RoomFacade;
import com.mangojelly.backend.global.common.Authenticated;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomFacade roomFacade;

    @GetMapping
    public ResponseEntity<ApiResponse<Void>> test(@Authenticated int memberid){
        System.out.println(memberid);
        // member 정보를 다 가져오고
        // 근데 없으면 터트리기
        // 있으면 그제서야 room 생성해도 되는지 체크
        // @Validate 해야 error catch 잡아줌

        return ResponseEntity.ok(new ApiResponse<Void>(ResponseCode.API_SUCCESS_MEMBER_LOGIN));
    }
}
