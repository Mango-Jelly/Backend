package com.mangojelly.backend.applicatoin.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.SceneVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
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
    private static final String PATH = "sample/image";

    public void run() throws IOException {
        List<ScriptVo> scripts = loadScriptFile();
        for(ScriptVo scriptVo : scripts){
            Script script = saveScript(scriptVo.title(),PATH+"/"+scriptVo.title()+".png");
            for(SceneVo scene : scriptVo.scenes()){
                String imageUrl = s3FileUploader.uploadFile(PATH+"/"+scriptVo.title()+"/scene/"+scene.title(),PATH+"/"+scriptVo.title()+"/scene/"+scene.title()+".png");
                Scenario scenario =  scenarioService.save(script.getName()+"-"+scene.title(),scene.scenario());
                sceneService.save(script,scene.seq(),scene.title(),scenario.getId(),imageUrl);
            }
        }
    }


    private Script saveScript(String title, String image){
        String imageUrl = s3FileUploader.uploadFile(image,PATH+"/"+title);
        return scriptService.save(title,imageUrl);
    }


    private List<ScriptVo> loadScriptFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/Scripts.json");
        ScriptWrapper wrapper = objectMapper.readValue(resource.getInputStream(), ScriptWrapper.class);
        return wrapper.scripts();
    }
}
