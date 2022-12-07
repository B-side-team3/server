package com.bside.server.global.auth.security;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.common.repository.MemberRepository;
import com.bside.server.global.common.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // username 은 "userId@email.com" 형태
        Member member = memberRepository.findByEmail(username);
        // 가입한 적 없는 이메일이거나 가입 이력 있으나 탈퇴한 회원인 경우
        if (member == null || member.getIsDeleted().equals("Y"))
            new AuthenticationException(ErrorCode.UNKNOWN_USER);
        return new UserAdapter(member);

    }
}
