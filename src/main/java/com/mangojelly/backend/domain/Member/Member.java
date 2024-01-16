package com.mangojelly.backend.domain.member;

import com.mangojelly.backend.domain.movie.Movie;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.global.common.BaseEntity;
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
public class Member extends BaseEntity {
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
