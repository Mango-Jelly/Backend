package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoomCreateRequest(@NotBlank String title, @NotBlank String dpt){
    public RoomCreateRequest(String title, String dpt){
        this.title = title;
        this.dpt = dpt;
    }
}
