package com.mangojelly.backend.applicatoin.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.SceneVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
import com.mangojelly.backend.domain.role.RoleService;
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

    private static final String PATH = "sample/image";

    public void run() throws IOException {
        List<ScriptVo> scripts = loadScriptFile();
        for(ScriptVo scriptVo : scripts){
            System.out.println(scriptVo);
            Script script = saveScript(scriptVo.title());
            for(int i = 0; i < scriptVo.roles().size();i++){
                saveRole(script,scriptVo.roles().get(i),i+1);
            }
            for(SceneVo sceneVo : scriptVo.scenes()){
                saveScene(script, sceneVo);
            }
        }
    }

    private void saveScene(Script script, SceneVo sceneVo){
        String imageUrl = s3FileUploader.uploadFile(PATH+"/"+script.getName()+"/scene/"+sceneVo.seq()+".png");
        Scenario scenario =  scenarioService.save(script.getName()+"-"+sceneVo.title(),sceneVo.scenario());
        sceneService.save(script,sceneVo.seq(),sceneVo.title(),scenario.getId(),imageUrl);
    }

    private void saveRole(Script script, String roleName,int idx){
        String image = s3FileUploader.uploadFile(PATH+"/"+script.getName()+"/role/"+idx+".PNG");
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
