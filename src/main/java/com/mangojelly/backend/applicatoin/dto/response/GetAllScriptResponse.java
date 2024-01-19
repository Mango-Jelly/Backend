package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.script.Script;

import java.util.ArrayList;
import java.util.List;

public record GetAllScriptResponse(List<GetScriptResponse> scripts) {

    public GetAllScriptResponse(List<GetScriptResponse> scripts){
        this.scripts = scripts;
    }

    public static GetAllScriptResponse of(List<Script> scripts){
        List<GetScriptResponse> response = new ArrayList<>();
        for(Script script : scripts){
            response.add(GetScriptResponse.from(script));
        }
        return new GetAllScriptResponse(response);
    }
}
