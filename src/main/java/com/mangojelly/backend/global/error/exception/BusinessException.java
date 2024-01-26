package com.mangojelly.backend.global.error.exception;

import com.mangojelly.backend.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public static BusinessException of(ErrorCode errorCode){
        return new BusinessException(errorCode);
    }

    private BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
