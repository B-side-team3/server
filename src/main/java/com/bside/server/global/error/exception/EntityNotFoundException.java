package com.bside.server.global.error.exception;

import com.bside.server.global.error.ErrorCode;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends CustomException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.NOT_FOUND);
    }

}
