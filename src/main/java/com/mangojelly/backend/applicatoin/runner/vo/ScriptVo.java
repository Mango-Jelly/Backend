package com.mangojelly.backend.applicatoin.runner.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ScriptVo(@JsonProperty String title, @JsonProperty List<String> roles, @JsonProperty List<SceneVo> scenes) {
    public ScriptVo(String title, List<String> roles, List<SceneVo> scenes){
        this.title = title;
        this.roles = roles;
        this.scenes = scenes;
    }
}
