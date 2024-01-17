package com.mangojelly.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),


    //AUTH
    ERROR_CLIENT_BY_AUTHORIZATION_INFORMATION(400, "AUTH_01", "권한 정보가 없는 토큰입니다."),
    ERROR_CLIENT_BY_JWT_SIGNATURE_INVALID(401, "AUTH_02", "잘못된 서명입니다."),
    ERROR_CLINET_BY_JWT_KEY_EXPIERD(401, "AUTH_03", "만료된 토큰입니다."),
    ERROR_CLIENT_BY_JWT_NOT_SUPPORT(401, "AUTH_04", "지원하지 않는 토큰입니다."),
    ERROR_CLIENT_BY_JWT_KEY_INVALID(401, "AUTH_05", "잘못된 토큰입니다."),
    ERROR_CLIENT_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE(403, "AUTH_06", "해당 기능에 대한 권한이 없습니다."),
    ERROR_CLIENT_BY_AUTHORIZATION_IS_NECESSARY(401, "AUTH_07", "사용자 인증이 필요합니다."),
    ;
    private final int status;
    private final String code;
    private final String message;
}
