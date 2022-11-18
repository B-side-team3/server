package com.bside.server.global.error.exception;

import com.bside.server.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends CustomException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.UNAUTHORIZED);
    }

}
