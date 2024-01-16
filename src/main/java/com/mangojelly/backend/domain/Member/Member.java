package com.mangojelly.backend.domain.Member;

import com.mangojelly.backend.domain.Movie.Movie;
import com.mangojelly.backend.domain.Room.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "member")
    private final List<Movie> movieList = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Room room;

    @Builder
    public Member(String email,String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
