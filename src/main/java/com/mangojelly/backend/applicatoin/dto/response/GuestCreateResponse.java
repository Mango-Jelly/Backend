package com.mangojelly.backend.applicatoin.dto.response;

public record GuestCreateResponse(int guestId) {
    public GuestCreateResponse(int guestId){
        this.guestId = guestId;
    }

    public static GuestCreateResponse of(int guestId){
        return new GuestCreateResponse(guestId);
    }
}
