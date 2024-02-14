package com.mangojelly.backend.applicatoin.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.SceneVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
import com.mangojelly.backend.domain.guest.Guest;
import com.mangojelly.backend.domain.guest.GuestService;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.movie.MovieService;
import com.mangojelly.backend.domain.role.RoleService;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomService;
import com.mangojelly.backend.domain.scenario.Scenario;
import com.mangojelly.backend.domain.scenario.ScenarioService;
import com.mangojelly.backend.domain.scene.SceneService;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.domain.script.ScriptService;
import com.mangojelly.backend.global.common.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class InitialFacade {
    private final ObjectMapper objectMapper;
    private final ScriptService scriptService;
    private final SceneService sceneService;
    private final S3FileUploader s3FileUploader;
    private final ScenarioService scenarioService;
    private final RoleService roleService;
    private final MemberService memberService;
    private final MovieService movieService;
    private final RoomService roomService;
    private final GuestService guestService;

    private static final String PATH = "sample/image";
    private static final String movieDummyPath = "https://mongo-jelly.s3.ap-northeast-2.amazonaws.com/frontSampleVideo.mp4";

    public void run() throws IOException {
        saveMember();
        saveRoom(1, "망고 초등학교", "1학년 6반 꿈빛제 아기돼지삼형제",  "d0ea544a-2a97-4975-af8c-82d4901627b4", true);
        saveRoom(2, "망고 초등학교", "1학년 6반 꿈빛제 아기돼지삼형제",  "d0ea544a-2a97-4975-af8c-82d4901627b5", true);
        saveScript();
        saveMovie(1, 2, 1, true,  "망고 초등학교", "1학년 6반 2024 상반기 마음제");
        saveMovie(1, 2, 1, false,  "망고 초등학교", "사랑둥이들 연극 1학년 4반");
        saveMovie(1, 3, 1, true,  "망고 초등학교", "2학년 5반 2024 망고제");

        saveMovie(2, 1, 2, true,  "샛별 어린이집", "사랑반 2024 재롱잔치");
        saveMovie(2, 1, 2, false,  "샛별 어린이집", "바나나반 2024 재롱잔치");
        saveMovie(2, 5, 2, true,  "샛별 어린이집", "복숭아반 2021 재롱잔치");

        saveMovie(1, 3, 1, false,  "망고 초등학교", "1학년 2반 2024 상반기 마음제");
        saveMovie(1, 4, 1, true,  "망고 초등학교", "1학년 1반 2023 상반기 마음제");
        saveMovie(1, 4, 1, false,  "망고 초등학교", "1학년 5반 2023 상반기 마음제");

        saveMovie(2, 5, 2, false,  "샛별 어린이집", "사랑반 2024 재롱잔치");
        saveMovie(2, 6, 2, true,  "샛별 어린이집", "바나나반 2024 재롱잔치");
        saveMovie(2, 6, 2, false,  "샛별 어린이집", "복숭아반 2021 재롱잔치");

    }

    /**
     * Member save method
     */
    private void saveMember(){
        memberService.save("sangb@ssafy.com", "ssafy", "김상범");
        memberService.save("apple@ssafy.com", "ssafy", "강용민");
        memberService.save("south@ssafy.com", "ssafy", "김남준");
    }

    /**
     * Room save method
     * @param memberId  : member 의 ID
     * @param dpt       : room 의 dpt
     * @param title     : room 의 title
     * @param address   : room 의 String으로 된 UUID 형식의 address
     * @param visible   : room 의 공개 여부
     */
    private void saveRoom(int memberId,  String dpt, String title, String address, boolean visible){
        Member member = memberService.findById(memberId);
        roomService.save(title, dpt, member, visible, UUID.fromString(address));
    }

    /**
     * Movie save method
     * @param memberId  : member 의 ID
     * @param scriptId  : script 의 ID
     * @param roomId    : room 의 ID
     * @param visible   : movie 의 공개 여부
     * @param dpt       : movie 의 dpt
     * @param title     : movie 의 title
     */
    private void saveMovie(int memberId, int scriptId, int roomId, boolean visible, String dpt, String title){
        Member member = memberService.findById(memberId);
        Script script = scriptService.findById(scriptId);
        Room room = roomService.findById(roomId);

        List<Guest> guests = new ArrayList<>();
        guests.add(guestService.save("이승현", room, roleService.findById(3)));
        guests.add(guestService.save("김한슬", room, roleService.findById(4)));
        guests.add(guestService.save("윤서안", room, roleService.findById(5)));
        guests.add(guestService.save("박상진", room, roleService.findById(6)));

        movieService.save(member, script, visible, movieDummyPath, dpt, guests, title);
    }

    /**
     * script를 저장하는 메서드
     * Role & Scene 의 파일경로 = "TheThreeLittlePigs" 임시처리함.
     * @throws IOException
     */
    private void saveScript() throws IOException{
        List<ScriptVo> scripts = loadScriptFile();
        int scriptId = 1;
        for(ScriptVo scriptVo : scripts){
            String image = s3FileUploader.uploadFile(PATH+"/"+ scriptId++ +"/thumbnail.png");
            Script script = scriptService.save(scriptVo.title(),image);
            for(int i = 0; i < scriptVo.roles().size();i++){
                saveRole(script ,scriptVo.roles().get(i),i+1);
            }
            for(SceneVo sceneVo : scriptVo.scenes()){
                saveScene(script, sceneVo);
            }
        }
    }

    private void saveScene(Script script, SceneVo sceneVo){
        String imageUrl = s3FileUploader.uploadFile(PATH+"/"+script.getId()+"/scene/"+sceneVo.seq()+".png");
        Scenario scenario =  scenarioService.save(script.getName()+"-"+sceneVo.title(),sceneVo.scenario());
        sceneService.save(script,sceneVo.seq(),sceneVo.title(),scenario.getId(),imageUrl);
    }

    private void saveRole(Script script, String roleName, int idx){
        String image = s3FileUploader.uploadFile(PATH+"/"+script.getId()+"/role/"+idx+".png");
        roleService.save(script,roleName,image);
    }

    private List<ScriptVo> loadScriptFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/Scripts.json");
        ScriptWrapper wrapper = objectMapper.readValue(resource.getInputStream(), ScriptWrapper.class);
        return wrapper.scripts();
    }
}
