package com.mangojelly.backend.applicatoin.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.SceneVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.movie.MovieService;
import com.mangojelly.backend.domain.role.RoleService;
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

    private static final String PATH = "sample/image";

    public void run() throws IOException {
        List<ScriptVo> scripts = loadScriptFile();
        for(ScriptVo scriptVo : scripts){
            Script script = saveScript(scriptVo.title());
            for(int i = 0; i < scriptVo.roles().size();i++){
                saveRole(script,scriptVo.roles().get(i),i+1);
            }
            for(SceneVo sceneVo : scriptVo.scenes()){
                saveScene(script, sceneVo);
            }
        }
        saveMember("sangb@ssafy.com", "ssafy", "김상범");
        saveMember("apple@ssafy.com", "ssafy", "강용민");
        saveMember("south@ssafy.com", "ssafy", "김남준");

        Member member = memberService.findById(1);
        Script script = scriptService.findById(2);
        String title = "아기 돼지 삼형제 망고반(8세)";
        String dpt = "털보 초등학교";
        String party = "첫째돼지,이승헌,둘째돼지,김한슬,막내돼지,윤서안,늑대,박상진";
        String movieAddress = "https://mongo-jelly.s3.ap-northeast-2.amazonaws.com/frontSampleVideo.mp4";

        saveMovie(member, script, true,  movieAddress, dpt, party, title);
        saveRoom(title, dpt, member ,UUID.fromString("d0ea544a-2a97-4975-af8c-82d4901627b4"), true);
    }

    private void saveMovie(Member member, Script script, boolean visible, String address, String dpt, String party, String title){
        movieService.save(member, script, visible, address, dpt, party, title);
    }

    private void saveRoom(String title, String dpt, Member member, UUID address, boolean visible){
        roomService.save(title, dpt, member, visible, address);
    }

    private void saveMember(String email, String password, String nickname){
        memberService.save(email, password, nickname);
    }

    private void saveScene(Script script, SceneVo sceneVo){
        String imageUrl = s3FileUploader.uploadFile(PATH+"/"+script.getName()+"/scene/"+sceneVo.seq()+".png");
        Scenario scenario =  scenarioService.save(script.getName()+"-"+sceneVo.title(),sceneVo.scenario());
        sceneService.save(script,sceneVo.seq(),sceneVo.title(),scenario.getId(),imageUrl);
    }

    private void saveRole(Script script, String roleName,int idx){
        String image = s3FileUploader.uploadFile(PATH+"/"+script.getName()+"/role/"+idx+".png");
        roleService.save(script,roleName,image);
    }

    private Script saveScript(String title){
        String image = s3FileUploader.uploadFile(PATH+"/"+title+"/thumbnail.png");
        return scriptService.save(title,image);
    }

    private List<ScriptVo> loadScriptFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/Scripts.json");
        ScriptWrapper wrapper = objectMapper.readValue(resource.getInputStream(), ScriptWrapper.class);
        return wrapper.scripts();
    }
}
