package com.bside.server.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /**
     * ----- 에러 분류 ----
     * common    : 1000
     * 인증       : 2000
     * Category  : 3000
     * Routine   : 4000
     * Task      : 5000
     *
     */

    // common
    INVALID_INPUT_VALUE(1000, "Invalid input value."),
    INVALID_BINDING_VALUE(1001, "Invalid binding value."),
    METHOD_NOT_ALLOWED(1002, "Method not allowed." ),

    // 인증
    UNKNOWN_USER(2000, "알 수 없는 유저입니다."),
    INVALID_AUTHENTICATION_TYPE(2001, "유효하지 않은 인증 타입입니다."),
    AUTHORIZATION_HEADER_NOT_FOUND(2003, "Authorization 헤더가 없습니다."),
    EXPIRED_TOKEN(2004, "만료된 토큰입니다."),
    DELETED_USER(2005, "탈퇴한 회원입니다."),
    // 카테고리
    CATEGORY_NOT_FOUND(3000, "존재하지 않는 카테고리입니다."),
    CATEGORY_NAME_EMPTY(3001, "카테고리 이름이 없습니다."),

    // 루틴
    ROUTINE_NOT_FOUND(4000, "존재하지 않는 루틴입니다."),
    ROUTINE_TITLE_EMPTY(4001, "루틴의 이름이 없습니다."),
    ROUTINE_DESCRIPTION_EMPTY(4002, "루틴의 설명이 없습니다."),

    // Task
    TASK_NOT_FOUND(5000, "존재하지 않는 Task 입니다."),
    TASK_TITLE_EMPTY(5001, "Task 의 이름이 없습니다."),
    TASK_EXPECTED_TIME_EMPTY(5002, "Task 의 예상 시간이 없습니다.")
    ;

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
