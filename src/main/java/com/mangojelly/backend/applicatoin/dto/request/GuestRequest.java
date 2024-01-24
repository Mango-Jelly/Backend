package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record GuestRequest (@NotBlank String nickName, @NotBlank UUID address) {
    public GuestRequest(String nickName, UUID address){
        this.nickName = nickName;
        this.address = address;
    }
}
