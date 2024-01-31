package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.fixture.RoomFixture;
import com.mangojelly.backend.global.error.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Transactional
@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomRepository roomRepository;
    @Spy
    private RoomMapper roomMapper;

    @DisplayName("Save Test")
    @Nested
    class save{

        @Test
        @DisplayName("성공 : 해당 ID로 만들어진 방이 존재하지 않음.")
        void whenSuccess() {
            //GIVEN
            Room room = RoomFixture.ROOM_FIXTURE.create();
            given(roomRepository.findByMember(any())).willReturn(Optional.empty());

            //WHEN, THEN
            assertDoesNotThrow(()->{
                roomService.save(room.getTitle(), room.getDpt(), room.getMember());
            });
        }

        @Test
        @DisplayName("실패 : 해당 ID로 만들어진 방이 이미 존재함.")
        void whenFailByAlreadyExist() {
            //GIVEN
            Room room = RoomFixture.ROOM_FIXTURE.create();
            given(roomRepository.findByMember(any())).willReturn(Optional.of(room));

            //WHEN, THEN
            assertThrows(BusinessException.class, ()->{
                roomService.save(room.getTitle(), room.getDpt(), room.getMember());
            });
        }

    }
}
