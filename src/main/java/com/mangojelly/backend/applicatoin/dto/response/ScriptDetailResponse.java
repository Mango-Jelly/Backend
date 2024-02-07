package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.domain.script.Script;

import java.util.List;

public record ScriptDetailResponse(List<SceneResponse> scenes, String title, String duration, String thumbnail, int reqPerson, List<RoleResponse> roles) {

    public static ScriptDetailResponse of(List<SceneResponse> scenes, Script script, List<RoleResponse> roles){
        return new ScriptDetailResponse(scenes, script.getName(), scenes.size() * 5 + "분", script.getImage(), roles.size(), roles);
    }

    public ScriptDetailResponse(List<SceneResponse> scenes, String title, String duration, String thumbnail, int reqPerson, List<RoleResponse> roles){
        this.scenes = scenes;
        this.title = title;
        this.duration = duration;
        this.thumbnail = thumbnail;
        this.reqPerson = reqPerson;
        this.roles = roles;
    }
}
