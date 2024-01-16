package com.mangojelly.backend.domain.Role;

import com.mangojelly.backend.domain.Guest.Guest;
import com.mangojelly.backend.domain.Room.Room;
import com.mangojelly.backend.domain.Script.Script;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id",nullable = false)
    private Script script;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Builder
    public Role(Script script, String name, String image){
        this.name = name;
        this.image = image;
        addRelatedByScript(script);
    }
    private void addRelatedByScript(Script script){
        this.script = script;
        script.getRoleList().add(this);
    }
}
