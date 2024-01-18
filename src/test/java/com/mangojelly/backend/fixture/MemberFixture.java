package com.mangojelly.backend.fixture;

import com.mangojelly.backend.domain.member.Member;

public enum MemberFixture {
    MEMBER_FIXTURE("test@example.com","tester","password")
    ;

    MemberFixture(String email, String nickname, String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    private final String email;
    private final String nickname;
    private final String password;

    public Member create(){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
