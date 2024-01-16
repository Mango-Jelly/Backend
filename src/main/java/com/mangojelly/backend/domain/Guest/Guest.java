package com.mangojelly.backend.domain.Guest;

import com.mangojelly.backend.domain.Role.Role;
import com.mangojelly.backend.domain.Room.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Guest {
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
