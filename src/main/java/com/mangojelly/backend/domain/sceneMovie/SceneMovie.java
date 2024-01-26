package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SceneMovie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_movie_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id",nullable = false)
    private Scene scene;

    @Setter
    @Column(nullable = false)
    private String address;

    @Builder
    public SceneMovie(Room room, Scene scene,String address){
        this.scene = scene;
        this.address = address;
        addRelatedByRoom(room);
    }

    private void addRelatedByRoom(Room room){
        this.room = room;
        room.getSceneMovieList().add(this);
    }
}
