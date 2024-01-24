package com.mangojelly.backend.domain.guest;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomRepository;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GuestService {
    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    @Transactional
    public Guest save(String name, Room room){
        return guestRepository.save(guestMapper.toEntity(name, room));
    }
}
