package com.mangojelly.backend.domain.script;

import org.springframework.stereotype.Component;

@Component
public class ScriptMapper {
    Script toEntity(String name, String image){
        return Script.builder()
                .name(name)
                .image(image)
                .build();
    }

}