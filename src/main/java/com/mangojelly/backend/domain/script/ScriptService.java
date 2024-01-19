package com.mangojelly.backend.domain.script;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScriptService {
    private final ScriptRepository scriptRepository;

    public List<Script> findAll() {
        return scriptRepository.findAll();
    }
}
