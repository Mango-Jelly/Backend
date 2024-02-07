package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByIdAndScript(int id, Script script);
    List<Role> findByScript(Script script);
    Optional<Role> findByScriptAndName(Script script, String name);
}
