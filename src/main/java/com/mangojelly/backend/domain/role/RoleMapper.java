package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import org.springframework.stereotype.Component;

@Component
class RoleMapper {
    public Role toEntity(Script script, String name, String image){
        return Role.builder().script(script).name(name).image(image).build();
    }
}
