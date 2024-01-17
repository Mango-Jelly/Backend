package com.mangojelly.backend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Optional<Member> findByEmailWithOptional(String email){
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public Member save(String email, String password, String nickname) {
        return memberRepository.save(memberMapper.toEntity(email,password,nickname));
    }
}
