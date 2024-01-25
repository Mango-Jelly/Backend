package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GuestRequest (@NotBlank String nickName, @NotNull UUID address) {
    public GuestRequest(String nickName, UUID address){
        this.nickName = nickName;
        this.address = address;
    }
}
