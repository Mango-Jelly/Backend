package com.mangojelly.backend.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findById(int id);
}
