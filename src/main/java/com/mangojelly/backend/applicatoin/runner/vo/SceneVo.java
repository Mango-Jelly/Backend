package com.mangojelly.backend.applicatoin.runner.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SceneVo(@JsonProperty String title, @JsonProperty int seq ,@JsonProperty List<String> scenario) {

    public SceneVo(String title, int seq, List<String> scenario){
        this.title = title;
        this.seq = seq;
        this.scenario = scenario;
    }
}
