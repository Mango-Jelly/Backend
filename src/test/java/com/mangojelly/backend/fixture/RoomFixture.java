package com.mangojelly.backend.fixture;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.room.Room;
import lombok.AllArgsConstructor;


public enum RoomFixture {

    ROOM_FIXTURE("mangojelly", "ssafy");

    RoomFixture(String title, String dpt){
        this.title = title;
        this.dpt = dpt;
    }

    private final String title;
    private final String dpt;

    public Room create(){
        return Room.builder()
                .title(title)
                .dpt(dpt)
                .member(MemberFixture.MEMBER_FIXTURE.create())
                .build();
    }
}
