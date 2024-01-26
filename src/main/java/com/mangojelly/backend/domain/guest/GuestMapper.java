package com.mangojelly.backend.domain.guest;

import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.room.Room;
import org.springframework.stereotype.Component;

@Component
class GuestMapper {
    Guest toEntity(String name, Room room){
        return Guest.builder()
                .name(name)
                .room(room)
                .build();
    }
}
