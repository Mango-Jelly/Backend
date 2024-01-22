package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.request.RoomCreateRequest;
import com.mangojelly.backend.applicatoin.dto.response.RoomCreateResponse;
import com.mangojelly.backend.applicatoin.facade.RoomFacade;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.global.common.Authenticated;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomFacade roomFacade;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<RoomCreateResponse>> availableCreateRoom(@Authenticated int memberId){
        RoomCreateResponse response = roomFacade.existRoomByMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_CHECK, response));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RoomCreateResponse>> createRoom(@Authenticated int memberId, @RequestBody @Validated RoomCreateRequest request){
        RoomCreateResponse response = roomFacade.saveRoom(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.API_SUCCESS_ROOM_CREATE, response));
    }

    @DeleteMapping("/{address}")
    public ResponseEntity<ApiResponse<Void>> deleteRoom(@Authenticated int memberId, @PathVariable(name = "address") UUID address){
        roomFacade.deleteRoom(memberId, address);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_ROOM_DELETE));
    }
}

//reponse class
// 오류 처리
// notion update