package com.mangojelly.backend.domain.guest;

import com.mangojelly.backend.applicatoin.dto.request.RoomBeginRequest;
import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * InitialFacade 용 Role save 메서드
     * @param name : guest name
     * @param room : host room
     * @param role : play에 부여된 role
     * @return
     */
    @Transactional
    public Guest save(String name, Room room, Role role){
        Guest guest = guestRepository.save(guestMapper.toEntity(name, room));
        guest.setRole(role);
        return guest;
    }

    @Transactional
    public List<Guest> updateSession(int id, String session){
        Guest guest = findById(id);
        guest.setSession(session);
        return guestRepository.findAllByRoomId(guest.getRoom().getId());
    }

    @Transactional
    public Guest updateRole(int id, Role role){
        Guest guest = findById(id);
        guest.setRole(role);
        return guest;
    }

    @Transactional
    public Guest updateRole(Guest guest, Role role){
        guest.setRole(role);
        return guest;
    }

    public Guest findById(int id){
        return guestRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_GUEST_NOT_EXIST));
    }

    @Transactional
    public void deleteGuestById(int id){
        findById(id);
        guestRepository.deleteById(id);
    }

    public List<Guest> findAllByRoomId(int roomId){
        return guestRepository.findAllByRoomId(roomId);
    }
}
