package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public boolean validateDuplicateBy(Member member){
        if (roomRepository.existsByMember(member)) {
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_EXISTED);
        }
        return true;
    }

    @Transactional
    public Room save(String title, String dpt, Member member){
        return roomRepository.save(roomMapper.toEntity(title, dpt, member));
    }

    public Room deleteRoomByMember_Email(Member member) {
        return roomRepository.deleteByMember(member).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_DELETED));
    }
}
