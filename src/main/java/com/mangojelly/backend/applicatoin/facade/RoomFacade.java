package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.RoomCreateRequest;
import com.mangojelly.backend.applicatoin.dto.response.RoomCreateResponse;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
     * @return 해당 회원의 방 정보
     */
    public RoomCreateResponse existRoomByMember(int memberId) {
        Member member = memberService.findById(memberId);
        return RoomCreateResponse.of(roomService.findByMember(member).getAddress());
    }

    /**
     *  방 생성 메서드
     * @param memberId, request
     * @return 생성한 방 객체
     */
    @Transactional
    public RoomCreateResponse saveRoom(int memberId, RoomCreateRequest request){
        Member member = memberService.findById(memberId);
        return RoomCreateResponse.of(roomService.save(request.title(), request.department(), member).getAddress());
    }

    /**
     * 방 삭제 메서드
     * @param address
     */
    @Transactional
    public void deleteRoom(int memberId, UUID address){
        Member member = memberService.findById(memberId);
        roomService.deleteRoomByMember(address, member);
    }
}
