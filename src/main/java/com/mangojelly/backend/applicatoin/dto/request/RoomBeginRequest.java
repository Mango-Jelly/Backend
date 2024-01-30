package com.mangojelly.backend.applicatoin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public record RoomBeginRequest (@NotNull List<Players> guests, @NotNull int script_id){

    public RoomBeginRequest(List<Players> guests, int script_id){
        this.guests = guests;
        this.script_id = script_id;
    }

    public static RoomBeginRequest of(List<Players> guests, int script_id){
        List<Players> response = new ArrayList<>();
        for(Players p: guests){
            response.add(Players.from(p));
        }
        return new RoomBeginRequest(response, script_id);
    }
    public record Players(@NotNull int guest_id, Integer role_id) {

        public Players(int guest_id, Integer role_id){
            this.guest_id = guest_id;
            this.role_id = role_id;
        }

        public static Players from(Players p){
            return new Players(p.guest_id, p.role_id);
        }
    }
}

