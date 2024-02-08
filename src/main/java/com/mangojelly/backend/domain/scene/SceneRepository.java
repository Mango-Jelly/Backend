package com.mangojelly.backend.domain.scene;

import com.mangojelly.backend.domain.script.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SceneRepository extends JpaRepository<Scene,Integer> {
    List<Scene> findByScript(Script script);
}
