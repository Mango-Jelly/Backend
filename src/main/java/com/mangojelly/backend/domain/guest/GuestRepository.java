package com.mangojelly.backend.domain.guest;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest,Integer> {
    Optional<Guest> findById(int id);
    Integer deleteById(int id);
}

