package com.mangojelly.backend.domain.member;

import org.springframework.stereotype.Component;

@Component
class MemberMapper {
    Member toEntity(String email,String password, String nickname){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
