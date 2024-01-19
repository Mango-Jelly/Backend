package com.mangojelly.backend.domain.scene;

import com.mangojelly.backend.domain.script.Script;
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
}
