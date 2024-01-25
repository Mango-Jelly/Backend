package com.mangojelly.backend.domain.guest;

import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Guest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @Setter
    private Role role;

    @Column(nullable = false)
    private String name;

    @Setter
    private String session; // rtc 에서 나오는 정보

    @Builder
    public Guest(String name, String session, Room room, Role role){
        this.name = name;
        this.session = session;
        this.role = role;
        addRelatedByRoom(room);
    }

    private void addRelatedByRoom(Room room){
        this.room = room;
        room.getGuestList().add(this);
    }
}
