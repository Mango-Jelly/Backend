package com.mangojelly.backend.domain.guest;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface GuestRepository extends JpaRepository<Guest,Integer> {
    List<Guest> findAllByRoomId(int id);
}

