package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Integer deleteByAddress(UUID address);
    boolean existsByMember(Member member);
    Optional<Room> findByAddress(UUID address);
    boolean existsByMemberAndAddress(Member member, UUID address);
    Optional<Room> findByMember(Member member);
    Optional<Room> findByMemberAndAddress(Member member, UUID address);
}
