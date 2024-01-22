package com.mangojelly.backend.fixture;

import com.mangojelly.backend.domain.scene.Scene;

public enum SceneFixture {
    SCENE_FIXTURE1(1,"sample-sampleScene1","sampleScene1","sampleScene1"),
    SCENE_FIXTURE2(2,"sample-sampleScene2","sampleScene2","sampleScene"),
    ;

    private final int seq;

    private final String address;
    private final String title;
    private final String background;

    SceneFixture (int seq, String address, String title, String background){
        this.seq = seq;
        this.address = address;
        this.title = title;
        this.background = background;
    }

    public Scene create(){
        return Scene.builder()
                .script(ScriptFixture.SCRIPT_FIXTURE.create())
                .seq(seq)
                .address(address)
                .title(title)
                .background(background).build();
    }
}
