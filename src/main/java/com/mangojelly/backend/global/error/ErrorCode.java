package com.mangojelly.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),

    API_ERROR_MEMBER_ALREADY_EXISTED(400,"M001","이미 등록되어있는 계정입니다."),
    API_ERROR_MEMBER_NOT_EXIST(400,"M002","존재하지 않는 회원입니다."),

    //AUTH
    ERROR_CLIENT_BY_AUTHORIZATION_INFORMATION(400, "AUTH001", "권한 정보가 없는 토큰입니다."),
    ERROR_CLIENT_BY_JWT_SIGNATURE_INVALID(401, "AUTH002", "잘못된 서명입니다."),
    ERROR_CLINET_BY_JWT_KEY_EXPIERD(401, "AUTH003", "만료된 토큰입니다."),
    ERROR_CLIENT_BY_JWT_NOT_SUPPORT(401, "AUTH004", "지원하지 않는 토큰입니다."),
    ERROR_CLIENT_BY_JWT_KEY_INVALID(401, "AUTH005", "잘못된 토큰입니다."),
    ERROR_CLIENT_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE(403, "AUTH006", "해당 기능에 대한 권한이 없습니다."),
    ERROR_CLIENT_BY_AUTHORIZATION_IS_NECESSARY(401, "AUTH007", "사용자 인증이 필요합니다."),


    ;
    private final int status;
    private final String code;
    private final String message;
}
