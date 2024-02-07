package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.role.Role;

public record RoleResponse(int roleId, String roleImg, String roleName) {

    public static RoleResponse from(Role role){
        return new RoleResponse(role.getId(), role.getImage(), role.getName());
    }
    public RoleResponse(int roleId, String roleImg, String roleName){
        this.roleId = roleId;
        this.roleImg = roleImg;
        this.roleName = roleName;
    }
}
