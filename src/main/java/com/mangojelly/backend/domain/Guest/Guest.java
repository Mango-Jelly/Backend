package com.mangojelly.backend.domain.guest;

import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Guest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    private String session;

    @Builder
    public Guest(String name, String session, Room room,Role role){
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
