package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.response.GetAllScriptResponse;
import com.mangojelly.backend.applicatoin.facade.ScriptFacade;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/script")
public class ScriptController {
    final private ScriptFacade scriptFacade;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GetAllScriptResponse>> getScripts() {
        GetAllScriptResponse response = scriptFacade.getAllScripts();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_SCRIPTS_GET, response));
    }
}
