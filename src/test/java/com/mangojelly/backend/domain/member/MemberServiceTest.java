package com.mangojelly.backend.domain.member;

import com.mangojelly.backend.fixture.MemberFixture;
import com.mangojelly.backend.global.error.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Transactional
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Spy
    private MemberMapper memberMapper;
    @Spy
    private PasswordEncoder passwordEncoder;

    @DisplayName("Save 테스트")
    @Nested
    class save{

        @Test
        @DisplayName("성공했을 때")
        void whenSuccess() {
            //GIVEN
            Member member = MemberFixture.MEMBER_FIXTURE.create();
            given(memberRepository.existsByEmail(any())).willReturn(false);
            given(memberRepository.save(any())).willReturn(member);

            //WHEN, THEN
            assertDoesNotThrow(()->{
                memberService.save(member.getEmail(), member.getPassword(), member.getNickname());
            });
        }

        @Test
        @DisplayName("실패, 이미 존재하는 계정일 때")
        void whenFailByDuplicated() {
            //GIVEN
            Member member = MemberFixture.MEMBER_FIXTURE.create();
            given(memberRepository.existsByEmail(any())).willReturn(true);

            //WHEN, THEN
            assertThrows(BusinessException.class,()->{
                memberService.save(member.getEmail(), member.getPassword(), member.getNickname());
            });
        }
    }
}