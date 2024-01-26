package com.mangojelly.backend.applicatoin.dto.response;

import java.util.UUID;

public record RoomCreateResponse(UUID address) {
    public RoomCreateResponse(UUID address){
        this.address = address;
    }

    public static RoomCreateResponse of(UUID address){
        return new RoomCreateResponse(address);
    }
}
