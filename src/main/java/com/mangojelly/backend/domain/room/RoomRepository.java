package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Optional<Room> deleteByMember(Member member);
    boolean existsByMember(Member member);
    Optional<Room> findByMember(Member member);
}
