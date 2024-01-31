package com.mangojelly.backend.global.response.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    API_SUCCESS_DOMAIN_METHOD("EXAMPLE001","예상 메시지입니다."),

    //MEMBER
    API_SUCCESS_MEMBER_SAVE("M001","멤버를 정상적으로 등록했습니다."),
    API_SUCCESS_MEMBER_LOGIN("M002","정상적으로 로그인했습니다."),
    API_SUCCESS_MEMBER_CHECK("M003", "멤버가 정상적으로 조회되었습니다."),
    API_SUCCESS_GUEST_CREATE("M004", "게스트를 정상적으로 등록했습니다."),
    API_SUCCESS_GUEST_UPDATE("M005", "게스트 정보를 정상적으로 수정했습니다."),
    API_SUCCESS_GUEST_DELETE("M006", "게스트 정보를 정상적으로 삭제했습니다."),


    //ROOM
    API_SUCCESS_ROOM_CREATE("R001", "방이 정상적으로 생성되었습니다."),
    API_SUCCESS_ROOM_DELETE("R002", "방이 정상적으로 삭제되었습니다."),

    //MOVIE
    API_SUCCESS_MOVIE_SCENE_CREATE("S001","씬 영상을 정상적으로 등록했습니다."),

    API_SUCCESS_MOVIES_READ("V001","모든 연극을 호출했습니다."),

    //Script
    API_SUCCESS_SCRIPTS_READ("SC001","모든 스크립트를 호출했습니다."),
    API_SUCCESS_ONESCRIPT_READ("SC002","해당 스크립트를 호출했습니다."),
    ;
    private final String code;
    private final String message;
}
