package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoomCreateRequest(@NotBlank String title, @NotBlank String department){
    public RoomCreateRequest(String title, String department){
        this.title = title;
        this.department = department;
    }
}
