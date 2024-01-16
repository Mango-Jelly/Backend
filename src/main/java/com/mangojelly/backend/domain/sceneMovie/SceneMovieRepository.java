package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.scene.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneMovieRepository extends JpaRepository<Scene,Integer> {
}
