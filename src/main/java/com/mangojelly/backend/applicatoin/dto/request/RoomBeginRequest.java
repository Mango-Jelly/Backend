package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public record RoomBeginRequest (@NotNull List<Players> guests, @NotNull int scriptId){

    public RoomBeginRequest(List<Players> guests, int scriptId){
        this.guests = guests;
        this.scriptId = scriptId;
    }

    public static RoomBeginRequest of(List<Players> guests, int scriptId){
        List<Players> response = new ArrayList<>();
        for(Players p: guests){
            response.add(Players.from(p));
        }
        return new RoomBeginRequest(response, scriptId);
    }
    public record Players(@NotNull int guestId, Integer roleId) {

        public Players(int guestId, Integer roleId){
            this.guestId = guestId;
            this.roleId = roleId;
        }

        public static Players from(Players p){
            return new Players(p.guestId, p.roleId);
        }
    }
}

