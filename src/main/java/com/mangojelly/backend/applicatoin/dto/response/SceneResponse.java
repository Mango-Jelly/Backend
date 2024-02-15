package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.scene.Scene;

import java.util.List;

public record SceneResponse(int seq, String title, String background, List<DialogResponse> dialogs) {

    public static SceneResponse of(Scene scene, List<DialogResponse> dialogs){
        return new SceneResponse(scene.getSeq(), scene.getTitle(), scene.getBackground(),dialogs);
    }
    public SceneResponse(int seq, String title, String background, List<DialogResponse> dialogs){
        this.seq = seq;
        this.title = title;
        this.background = background;
        this.dialogs = dialogs;
    }
}
