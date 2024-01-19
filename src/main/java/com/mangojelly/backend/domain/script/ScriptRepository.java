package com.mangojelly.backend.domain.script;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Integer> {
    List<Script> findAll();
}
