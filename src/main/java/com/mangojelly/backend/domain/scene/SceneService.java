package com.mangojelly.backend.domain.scene;

import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SceneService {
    private final SceneRepository sceneRepository;
    private final SceneMapper sceneMapper;

    @Transactional
    public Scene save(Script script, int seq, String title, String address, String background){
        return sceneRepository.save(sceneMapper.toEntity(script,seq,title,address,background));
    }

    public Scene findById(int id) {
        return sceneRepository.findById(id).orElseThrow(()-> BusinessException.of(ErrorCode.API_ERROR_SCENE_NOT_EXIST));
    }
}
