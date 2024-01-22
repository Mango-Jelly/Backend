package com.mangojelly.backend.applicatoin.runner.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ScriptWrapper(@JsonProperty List<ScriptVo> scripts) {
    public ScriptWrapper(List<ScriptVo> scripts){
        this.scripts = scripts;
    }
}
