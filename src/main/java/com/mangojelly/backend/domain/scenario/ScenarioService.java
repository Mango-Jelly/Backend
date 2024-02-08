package com.mangojelly.backend.domain.scenario;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;

    @Transactional
    public Scenario save(String id, List<String> scenarioRowList){
        return scenarioRepository.save(Scenario.from(id,scenarioRowList));
    }

    public Scenario findById(String id){
        return scenarioRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_SCENE_NOT_EXIST));
    }

    public List<Scenario> findAllByScript(String sceneTitle){
        return scenarioRepository.findAllByIdContaining(sceneTitle);
    }
}
