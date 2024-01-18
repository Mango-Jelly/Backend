package com.mangojelly.backend.applicatoin.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MangoJellyApplicationRunner implements ApplicationRunner {

    private final ObjectMapper objectMapper;
//    private final ScriptService scriptService;
//    private final SceneService sceneService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ScriptVo> scripts = loadScriptFile();
    }

    private List<ScriptVo> loadScriptFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/Scripts.json");
        ScriptWrapper wrapper = objectMapper.readValue(resource.getInputStream(), ScriptWrapper.class);
        return wrapper.scripts();
    }
}
