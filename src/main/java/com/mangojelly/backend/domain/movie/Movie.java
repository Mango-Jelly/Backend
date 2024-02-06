package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private Script script;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String party;

    @Column(nullable = false)
    private String dpt;

    @Column
    private boolean visible;

    @Builder
    public Movie(Member member, Script script, String address, String title,String party,String dpt,boolean visible){
        this.script = script;
        this.address = address;
        this.title = title;
        this.party = party;
        this.dpt = dpt;
        this.visible = visible;
        addRelatedByMember(member);
    }

    private void addRelatedByMember(Member member){
        this.member = member;
        member.getMovieList().add(this);
    }
}
