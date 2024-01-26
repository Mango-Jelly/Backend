package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuestRoleRequest(@NotNull int id, @NotNull int role) {
    public GuestRoleRequest(int id, int role){
        this.id = id;
        this.role = role;
    }
}