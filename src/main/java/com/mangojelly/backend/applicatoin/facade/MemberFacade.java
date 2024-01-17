package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.SignupRequest;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;

    /**
     * 멤버를 등록하는 메소드
     * @param request
     */
    @Transactional
    public void saveMember(SignupRequest request) {
        if(memberService.findByEmailWithOptional(request.email()).isPresent())
            throw BusinessException.of(ErrorCode.API_ERROR_MEMBER_ALREADY_EXISTED);
        memberService.save(request.email(), request.password(), request.nickname());
    }

}
