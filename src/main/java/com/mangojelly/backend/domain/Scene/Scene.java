package com.mangojelly.backend.domain.Scene;

import com.mangojelly.backend.domain.Script.Script;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private Script script;

    @Column(nullable = false)
    private int seq;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String background;

    @Builder
    public Scene(Script script, int seq, String address, String title, String background){
        this.seq = seq;
        this.address = address;
        this.title = title;
        this.background = background;
        addRelatedByScript(script);
    }

    private void addRelatedByScript(Script script){
        this.script = script;
        script.getSceneList().add(this);
    }
}
