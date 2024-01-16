package com.mangojelly.backend.domain.Room;

import com.mangojelly.backend.domain.Guest.Guest;
import com.mangojelly.backend.domain.Member.Member;
import com.mangojelly.backend.domain.Scene.Scene;
import com.mangojelly.backend.domain.SceneMovie.SceneMovie;
import com.mangojelly.backend.domain.Script.Script;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Room {

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
    private Script script;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private final List<SceneMovie> sceneMovieList = new ArrayList<>();

    @Builder
    public Room(Member member, Script script, String title, String dpt){
        this.member = member;
        this.script = script;
        this.title = title;
        this.dpt = dpt;
    }
}
