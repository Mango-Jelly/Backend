package com.mangojelly.backend.global.response.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ApiBaseResponse<T> {

    private final String message;
    private Object data;

    @JsonCreator
    public ApiBaseResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
    }

    @JsonCreator
    public ApiBaseResponse(ResponseCode responseCode, Object data) {
        this.message = responseCode.getMessage();
        this.data = data;
    }
}
