package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.role.Role;

import java.util.List;

public record DialogResponse(List<SceneRoleResponse> roles, String dialog) {

    public static DialogResponse of(List<SceneRoleResponse> roles, String dialog){
        return new DialogResponse(roles, dialog);
    }

    public DialogResponse(List<SceneRoleResponse> roles, String dialog){
        this.roles = roles;
        this.dialog = dialog;
    }
}
