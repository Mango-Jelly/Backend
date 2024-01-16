package com.mangojelly.backend.domain.script;

import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.scene.Scene;
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
public class Script extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "script_id")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "script",cascade = CascadeType.ALL)
    private final List<Scene> sceneList = new ArrayList<>();

    @OneToMany(mappedBy = "script",cascade = CascadeType.ALL)
    private final List<Role> roleList = new ArrayList<>();

    @Builder
    public Script(String name, String image){
        this.name = name;
        this.image = image;
    }
}
