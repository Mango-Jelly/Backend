package com.mangojelly.backend.global.response.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    API_SUCCESS_DOMAIN_METHOD("EXAMPLE001","예상 메시지입니다.");

    private final String code;
    private final String message;
}
