package com.mangojelly.backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
