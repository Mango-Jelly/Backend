package com.mangojelly.backend.domain.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptVo;
import com.mangojelly.backend.applicatoin.runner.vo.ScriptWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional(readOnly = true)
@SpringBootTest
class ScenarioServiceTest {

    @Autowired
    private ScenarioService scenarioService;

    @Test
    @Transactional
    void save() {
        assertDoesNotThrow(()->{
            List<ScriptVo> scriptVoList = loadScriptFile();
            List<String> scenario = scriptVoList.get(0).scenes().get(0).scenario();
            scenarioService.save("sample",scenario);
        });
    }

    private List<ScriptVo> loadScriptFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("sample/Scripts.json");
        ObjectMapper objectMapper = new ObjectMapper();
        ScriptWrapper wrapper = objectMapper.readValue(resource.getInputStream(), ScriptWrapper.class);
        return wrapper.scripts();
    }
}