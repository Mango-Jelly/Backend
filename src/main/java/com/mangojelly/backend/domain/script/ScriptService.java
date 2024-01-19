package com.mangojelly.backend.domain.script;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScriptService {
    private final ScriptRepository scriptRepository;
    private final ScriptMapper scriptMapper;

    @Transactional
    public Script save(String title, String image){
        return scriptRepository.save(scriptMapper.toEntity(title,image));
    }
}
