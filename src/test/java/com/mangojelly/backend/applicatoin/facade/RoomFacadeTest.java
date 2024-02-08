package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomService;
import com.mangojelly.backend.domain.sceneMovie.SceneMovieService;
import com.mangojelly.backend.global.error.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void saveMovie() throws IOException {
        Member member = memberService.findById(1);
        Room room = member.getRoom();
        for(int i = 0; i < room.getScript().getSceneList().size();i ++){
            ClassPathResource resource = new ClassPathResource("sample/videos/" +"00"+(i+1)+".mp4");
            MultipartFile multipartFile = new MockMultipartFile(UUID.randomUUID()+".mp4",resource.getInputStream());
            sceneMovieService.save(room,room.getScript().getSceneList().get(i),multipartFile);
        }

        assertDoesNotThrow(()->{
            roomFacade.saveMovie(member.getId(),room.getAddress());
        });
    }

    @DisplayName("SceneMovie 로컬에 저장 Test")
    @Test
    void loadSceneMovieTest(){
        assertDoesNotThrow(()->{
            roomFacade.loadSceneMovie("https://mongo-jelly.s3.ap-northeast-2.amazonaws.com/frontSampleVideo.mp4");
        });
    }

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