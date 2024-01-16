package com.mangojelly.backend.domain.SceneMovie;

import com.mangojelly.backend.domain.Room.Room;
import com.mangojelly.backend.domain.Scene.Scene;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SceneMovie {
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
