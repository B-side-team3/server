package com.bside.server.global.auth.security;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.member.repository.MemberRepository;
import com.bside.server.member.domain.Member;
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
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_USER));
        return new UserAdapter(member);

    }
}
