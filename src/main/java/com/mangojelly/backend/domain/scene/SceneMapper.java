package com.mangojelly.backend.domain.scene;

import com.mangojelly.backend.domain.script.Script;
import org.springframework.stereotype.Component;

@Component
public class SceneMapper {
    public Scene toEntity(Script script, int seq, String title, String address, String background){
        return Scene.builder().script(script).seq(seq).title(title).address(address).background(background).build();
    }
}
