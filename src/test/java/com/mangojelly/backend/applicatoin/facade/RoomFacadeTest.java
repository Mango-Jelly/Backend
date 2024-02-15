package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomService;
import com.mangojelly.backend.domain.sceneMovie.SceneMovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@SpringBootTest
class RoomFacadeTest {

    @Autowired
    private RoomFacade roomFacade;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SceneMovieService sceneMovieService;

    @Test
    void existRoomByMember() {
    }

    @Test
    void saveRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Deprecated
    @Test
    void saveMovie(){
        Member member = memberService.findById(1);
        Room room = member.getRoom();
        for(int i = 0; i < room.getScript().getSceneList().size();i ++){
            ClassPathResource resource = new ClassPathResource("sample/videos/" +"00"+(i+1)+".mp4");
            sceneMovieService.save(room,room.getScript().getSceneList().get(i),resource.getPath());
        }

        assertDoesNotThrow(()->{
            roomFacade.saveMovie(member.getId(),room.getAddress());
        });
    }

    @Deprecated
    @Test
    void concatSceneMovie(){
        assertDoesNotThrow(()->{
            List<String> sceneMovieList = new ArrayList<>();
            for(int i = 1; i <= 3; i++){
                sceneMovieList.add("00"+i+".mp4");
            }
            roomFacade.concatSceneMovie("testFinal",sceneMovieList);
        });
    }

    @Test
    void beginMovie() {
    }
}