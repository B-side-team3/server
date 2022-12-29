package com.bside.server.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Error error;

    public ErrorResponse(ErrorCode errorCode) {
        this.error = new Error(errorCode);
    }

    public ErrorResponse(int code, String message) {
        this.error = new Error(code, message);
    }

    @Getter
    @AllArgsConstructor
    public static class Error {
        private int code;
        private String message;

        public Error(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.message = errorCode.getMessage();
        }
    }

}