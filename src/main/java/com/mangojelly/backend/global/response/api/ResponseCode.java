package com.mangojelly.backend.global.response.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    API_SUCCESS_DOMAIN_METHOD("EXAMPLE001","예상 메시지입니다."),

    //MEMBER
    API_SUCCESS_MEMBER_SAVE("M001","멤버를 정상적으로 등록했습니다.");


    private final String code;
    private final String message;
}
