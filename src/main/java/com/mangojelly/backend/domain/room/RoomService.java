package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Room findByMember(Member member){
        return roomRepository.findByMember(member).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_NOT_EXISTED));
    }

    @Transactional
    public Room save(String title, String dpt, Member member){
        if (roomRepository.findByMember(member).orElse(null) != null)
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_EXISTED);
        return roomRepository.save(roomMapper.toEntity(title, dpt, member, UUID.randomUUID()));
    }

    public void deleteRoomByMember(UUID address, Member member) {
        Room room = roomRepository.findByAddress(address).orElseThrow(()->BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_DELETED));
        if (room.getMember().getId() != member.getId())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_IS_NOT_YOURS);
        roomRepository.deleteByAddress(address);
    }

}
