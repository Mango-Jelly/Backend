package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoomCreateRequest(@NotBlank String title, @NotBlank String department, boolean visible){
    public RoomCreateRequest(String title, String department, boolean visible){
        this.title = title;
        this.department = department;
        this.visible = visible;
    }
}
