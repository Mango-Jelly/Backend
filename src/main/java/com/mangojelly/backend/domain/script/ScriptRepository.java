package com.mangojelly.backend.domain.script;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ScriptRepository extends JpaRepository<Script,Integer> {
    List<Script> findAll();

//    Optional<Script> findByScriptId(int scriptId);
}
