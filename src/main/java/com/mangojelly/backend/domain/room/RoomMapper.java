package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoomMapper {
    Room toEntity(String title, String dpt, Member member, UUID address, boolean visible){
        return Room.builder()
                .title(title)
                .dpt(dpt)
                .address(address)
                .member(member)
                .visible(visible)
                .build();
    }
}
