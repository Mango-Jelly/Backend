package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoomFacade {
    private final RoomService roomService;
    private final MemberService memberService;

    /**
     *  방 생성 여부 체크
     */
    @Transactional
    public boolean existRoom(Member member) {
        return roomService.validateDuplicateBy(member);
    }

    /**
     *  방 생성하는 메소드
     */
    @Transactional
    public void saveRoom(){
        // roomCreateRequest & memberid : findById 해서 가져오기
    }
}
