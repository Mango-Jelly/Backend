package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.script.Script;

public record GetScriptResponse(int scriptId, String thumbnail, String title) {

    public static GetScriptResponse from(Script script){
        return new GetScriptResponse(script.getId(), script.getImage(), script.getName());
    }

    public GetScriptResponse(int scriptId, String thumbnail, String title){
        this.scriptId = scriptId;
        this.thumbnail = thumbnail;
        this.title = title;
    }
}
