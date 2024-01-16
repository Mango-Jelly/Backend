package com.mangojelly.backend.global.security.exception;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;

public class AuthException extends BusinessException {
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
