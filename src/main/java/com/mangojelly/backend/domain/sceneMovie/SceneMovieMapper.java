package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.scene.Scene;
import org.springframework.stereotype.Component;

@Component
public class SceneMovieMapper {
    public SceneMovie toEntity(Room room, Scene scene, String movie){
        return SceneMovie.builder().room(room).scene(scene).address(movie).build();
    }
}
