package com.mangojelly.backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(int id);
    boolean existsByEmail(String email);
}
