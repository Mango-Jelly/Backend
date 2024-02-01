package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.guest.Guest;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.sceneMovie.SceneMovie;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,columnDefinition = "BINARY(16)")
    private UUID address;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String dpt;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private final List<Guest> guestList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    @Setter
    private Script script;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private final List<SceneMovie> sceneMovieList = new ArrayList<>();

    @Builder
    public Room(Member member, Script script, String title, String dpt, UUID address){
        this.member = member;
        this.script = script;
        this.title = title;
        this.address = address;
        this.dpt = dpt;
    }
}
