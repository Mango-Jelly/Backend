package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByIdAndScript(int id, Script script);
}
