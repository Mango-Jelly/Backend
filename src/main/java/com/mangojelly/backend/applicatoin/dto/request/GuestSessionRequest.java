package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GuestSessionRequest (@NotNull int id, @NotBlank String session) {
    public GuestSessionRequest(int id, String session){
        this.id = id;
        this.session = session;
    }
}