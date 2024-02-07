package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.scene.Scene;

import java.util.List;

public record SceneResponse(int seq, String title, List<DialogResponse> dialogs) {

    public static SceneResponse of(Scene scene, List<DialogResponse> dialogs){
        return new SceneResponse(scene.getSeq(), scene.getTitle(), dialogs);
    }
    public SceneResponse(int seq, String title, List<DialogResponse> dialogs){
        this.seq = seq;
        this.title = title;
        this.dialogs = dialogs;
    }
}
