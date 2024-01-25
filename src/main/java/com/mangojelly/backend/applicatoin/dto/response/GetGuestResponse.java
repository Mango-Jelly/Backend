package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.guest.Guest;

public record GetGuestResponse(int guest_id, String name, String token){

    public static GetGuestResponse from (Guest guest){
        return new GetGuestResponse(guest.getId(), guest.getName(), guest.getSession());
    }

    public GetGuestResponse(int guest_id, String name, String token){
        this.guest_id = guest_id;
        this.name = name;
        this.token = token;
    }
}
