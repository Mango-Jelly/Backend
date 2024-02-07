package com.mangojelly.backend.domain.script;

import com.mangojelly.backend.domain.scenario.Scenario;
import com.mangojelly.backend.domain.scenario.ScenarioRepository;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final ScriptMapper scriptMapper;
    private final ScenarioRepository scenarioRepository;

    @Transactional
    public Script save(String title, String image) {
        return scriptRepository.save(scriptMapper.toEntity(title, image));
    }

    public List<Script> findAll() {
        return scriptRepository.findAll();
    }

    public Script findById(int id){
        Script script = scriptRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_SCRIPT_NOT_EXIST));


        return script;
    }
}
