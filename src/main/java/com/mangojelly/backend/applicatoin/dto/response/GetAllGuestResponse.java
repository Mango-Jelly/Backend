package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.guest.Guest;

import java.util.ArrayList;
import java.util.List;

public record GetAllGuestResponse(List<GetGuestResponse> guests)  {

    public GetAllGuestResponse(List<GetGuestResponse> guests){
        this.guests = guests;
    }

    public static GetAllGuestResponse of(List<Guest> guests){
        List<GetGuestResponse> response = new ArrayList<>();
        for(Guest guest: guests){
            response.add(GetGuestResponse.from(guest));
        }
        return new GetAllGuestResponse(response);
    }
}
