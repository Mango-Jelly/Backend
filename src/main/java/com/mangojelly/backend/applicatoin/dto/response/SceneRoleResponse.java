package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.role.Role;

public record SceneRoleResponse(String roleName, String roleImg) {

    public static SceneRoleResponse from(Role role){
        return new SceneRoleResponse(role.getName(), role.getImage());
    }
    public SceneRoleResponse(String roleName, String roleImg){
        this.roleName = roleName;
        this.roleImg = roleImg;
    }
}
