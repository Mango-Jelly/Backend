package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.scene.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SceneMovieRepository extends JpaRepository<SceneMovie,Integer> {
    Optional<SceneMovie> findByRoomAndScene(Room room, Scene scene);
}
