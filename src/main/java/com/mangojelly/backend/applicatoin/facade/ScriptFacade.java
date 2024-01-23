package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.response.GetAllScriptResponse;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.domain.script.ScriptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScriptFacade {
    private final ScriptService scriptService;

    /**
     * 스크립트 리스트 메소드
     */
    public GetAllScriptResponse getAllScripts(){
        List<Script> scripts = scriptService.findAll();
        return GetAllScriptResponse.of(scripts);
    }

    public Script getScript(int id){
        return scriptService.findById(id);
    }
}
