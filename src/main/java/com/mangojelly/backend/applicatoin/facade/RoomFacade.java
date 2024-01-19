package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.RoomCreateRequest;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.Room;
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
     * @param memberId
     * @return 해당 회원의 방 생성 여부 boolean
     */
    public boolean existRoomByMember(int memberId) {
        Member member = memberService.findById(memberId);
        return roomService.validateDuplicateBy(member);
    }

    /**
     *  방 생성 메서드
     * @param memberId, request
     * @return 생성한 방 객체
     */
    @Transactional
    public Room saveRoom(int memberId, RoomCreateRequest request){
        Member member = memberService.findById(memberId);
        return roomService.save(request.title(), request.department(), member);
    }

    /**
     * 방 삭제 메서드
     * @param memberId
     */
    @Transactional
    public void deleteRoom(int memberId){
        Member member = memberService.findById(memberId);
        roomService.deleteRoomByMember(member);
    }
}
