package com.mangojelly.backend.domain.member;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
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

    private void validateDuplicateBy(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw BusinessException.of(ErrorCode.API_ERROR_MEMBER_ALREADY_EXISTED);
        }
    }

    @Transactional
    public Member save(String email, String password, String nickname) {
        validateDuplicateBy(email);
        return memberRepository.save(memberMapper.toEntity(email,password,nickname));
    }
}
