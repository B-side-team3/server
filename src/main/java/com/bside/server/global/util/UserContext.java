package com.bside.server.global.util;

import com.bside.server.global.auth.security.UserAdapter;
import com.bside.server.module.member.domain.Member;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * SecurityContextHolder 사용하여
 * 요청 별로 인증된 사용자 정보를 전역적으로 조회 가능하도록 한다.
 */
public final class UserContext {

    private UserContext() {}

    public static Member getMember() {
        return getUserAdapter().getMember();
    }

    private static UserAdapter getUserAdapter() {
        return (UserAdapter) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
