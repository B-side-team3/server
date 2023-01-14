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
     * Sign in   : 6000
     * firebase  : 7000
     * 마이페이지  : 8000
     */

    // common
    INVALID_INPUT_VALUE(1000, "유효하지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(1001, "허용되지 않은 method 입니다."),
    CONTENT_TYPE_NOT_ALLOWED(1002, "허용되지 않은 콘텐츠 타입입니다."),

    // 인증
    UNKNOWN_USER(2000, "알 수 없는 유저입니다."),
    INVALID_AUTHENTICATION_TYPE(2001, "유효하지 않은 인증 타입입니다."),
    AUTHORIZATION_HEADER_NOT_FOUND(2003, "Authorization 헤더가 존재하지 않습니다."),
    TOKEN_NOT_FOUND(2004, "토큰이 존재하지 않습니다."),
    INVALID_TOKEN(2005, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(2006, "만료된 토큰입니다."),
    DELETED_USER(2007, "탈퇴한 회원입니다."),
    INVALID_REFRESH_REQUEST(2008, "유효하지 않은 재발급 요청입니다."),
    NOT_ADMIN(2009, "관리자만 접근이 가능합니다."),

    // 카테고리
    CATEGORY_NOT_FOUND(3000, "존재하지 않는 카테고리입니다."),
    CATEGORY_NAME_EMPTY(3001, "카테고리 이름이 없습니다."),

    // 루틴
    ROUTINE_NOT_FOUND(4000, "존재하지 않는 루틴입니다."),
    ROUTINE_TITLE_EMPTY(4001, "루틴의 이름이 없습니다."),
    ROUTINE_DESCRIPTION_EMPTY(4002, "루틴의 설명이 없습니다."),
    NOT_FOUND_FILE(4003, "파일을 찾을 수 없습니다."),

    // Task
    TASK_NOT_FOUND(5000, "존재하지 않는 Task 입니다."),
    TASK_TITLE_EMPTY(5001, "Task 의 이름이 없습니다."),
    TASK_EXPECTED_TIME_EMPTY(5002, "Task 의 예상 시간이 없습니다."),

    // sign in
    FAIL_TO_GET_KAKAO_USER_INFO(6000, "카카오 서버에서 사용자 정보를 얻지 못했습니다."),
    INVALID_EMAIL(6001, "이메일이 유효하지 않습니다."),

    // firebase
    FAIL_TO_INIT_FIREBASE(7000, "firebase 초기화에 실패했습니다."),
    EMPTY_NOTIFICATION_TOKEN(7001, "저장할 notification token 값이 없습니다."),
    NOTIFICATION_MEMBER_NOT_FOUND(7002, "사용자를 찾을 수 없습니다."),

    // 마이페이지
    NOT_FOUND(8000, "정보를 찾을 수 없습니다.");

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