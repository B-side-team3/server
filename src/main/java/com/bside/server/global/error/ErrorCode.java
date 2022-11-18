package com.bside.server.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /**
     * ----- 에러 분류 ----
     * common : 1000
     * 인증   : 2000
     */

    // common
    INVALID_INPUT_VALUE(1000, "Invalid input value."),
    INVALID_BINDING_VALUE(1001, "Invalid binding value."),
    METHOD_NOT_ALLOWED(1002, "Method not allowed." );

    // 인증
//    UNKNOWN_USER(2000, "Unknown user."),

    private final int code;
    private final String message;

    ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode valueOfOrNull(String name) {
        try {
            return valueOf(name);
        } catch (Exception e) {
            return null;
        }
    }
}
