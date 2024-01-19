package com.mangojelly.backend.domain.script;

import org.springframework.stereotype.Component;

@Component
public class ScriptMapper {
    public Script toEntity(String title, String image){
        return Script.builder()
                .name(title)
                .image(image)
                .build();
    }
}
