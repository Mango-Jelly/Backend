package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Room findByMember(Member member) {
        return roomRepository.findByMember(member).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_NOT_EXISTED));
    }

    @Transactional
    public Room save(String title, String dpt, Member member, boolean visible) {
        if (roomRepository.findByMember(member).isPresent())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_EXISTED);
        return roomRepository.save(roomMapper.toEntity(title, dpt, member, UUID.randomUUID(), visible));
    }

    @Transactional
    public Room save(String title, String dpt, Member member, boolean visible, UUID address) {
        return roomRepository.save(roomMapper.toEntity(title, dpt, member, address, visible));
    }

    @Transactional
    public void deleteRoomByMember(UUID address, Member member) {
        Room room = roomRepository.findByAddress(address).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_DELETED));
        if (room.getMember().getId() != member.getId())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_IS_NOT_YOURS);
        roomRepository.deleteByAddress(address);
    }

    public Room findByAddress(UUID address) {
        return roomRepository.findByAddress(address).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_INPUT_INVALID_VALUE));
    }

    @Transactional
    public void saveMovie(Member member, UUID roomUUID) {


    }

    /**
     * 연극 시작 가능 여부 확인 함수
     * @param member
     * @param address
     * @return
     */
    public Room checkRoomBegin(Member member, UUID address){
        findByMember(member);
        return roomRepository.findByMemberAndAddress(member, address).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION));
    }

    @Transactional
    public Room updateScript(Room room, Script script){
        room.setScript(script);
        return room;
    }

}
