package com.mangojelly.backend.fixture;

import com.mangojelly.backend.domain.script.Script;

public enum ScriptFixture {
    SCRIPT_FIXTURE("sample","sample/thumbnail.png");

    private final String name;
    private final String image;

    ScriptFixture(String name, String image){
        this.name = name;
        this.image = image;
    }

    public Script create(){
        return Script.builder()
                .image(image)
                .name(name)
                .build();
    }
}
