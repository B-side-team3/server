package com.bside.server.module.auth.service;

import com.bside.server.global.auth.security.JwtValidator;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.auth.domain.Oauth;
import com.bside.server.module.auth.dto.TokenResponse;
import com.bside.server.module.auth.repository.OauthRepository;
import com.bside.server.module.auth.util.KakaoUserInfo;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final OauthRepository oauthRepository;
    private final JwtValidator jwtValidator;

    @Transactional
    public Member getMember(HttpServletRequest request) {
        String oauthToken = jwtValidator.getToken(request);
        JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(oauthToken);
        String email = String.valueOf(userInfo.path("kakao_account").get("email")).replace("\"", "");

        Optional<Member> member = memberRepository.findByEmailAndIsDeletedIs(email, false);

        // 신규 회원
        if (member.isEmpty()) {
            return memberRepository.saveAndFlush(Member.builder().email(email).isAdmin(false).isDeleted(false).build());
        }

        // 탈퇴 회원
        if (member.get().isDeleted()) {
            throw new CustomException(ErrorCode.DELETED_USER);
        }

        // 기존 회원
        return member.get();
    }

    public void validateRefresh(HttpServletRequest request) {
        String refreshToken = jwtValidator.getToken(request);
        Optional<Oauth> oauth = oauthRepository.findByMember(UserContext.getMember());

        // 토큰 데이터 없는 경우
        if (oauth.isEmpty()) {
            throw new AuthenticationException(ErrorCode.TOKEN_NOT_FOUND);
        }

        // db 와 값이 다른 경우
        if (!oauth.get().getRefreshToken().equals(refreshToken)) {
            throw new AuthenticationException(ErrorCode.INVALID_TOKEN);
        }

        // accessToken 이 만료되지 않았는데 refresh 요청 보낸 경우
        if (!jwtValidator.isTokenExpired(oauth.get().getAccessToken())) {
            oauthRepository.delete(oauth.get());
            throw new AuthenticationException(ErrorCode.INVALID_REFRESH_REQUEST);
        }

        // refreshToken 이 만료된 경우
        if (jwtValidator.isTokenExpired(oauth.get().getRefreshToken())) {
            oauthRepository.delete(oauth.get());
            throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
        }
    }

    @Transactional
    public TokenResponse upsertOauth(Member member) {
        final TokenResponse response = jwtValidator.createToken(member.getEmail());
        Oauth oauth = Oauth.builder()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .type("KAKAO")
                .member(member)
                .build();

        oauthRepository.findByMember(member).ifPresent(oauthRepository::delete);
        oauthRepository.save(oauth);
        return response;
    }

    @Transactional
    public void revoke() {
        Optional<Oauth> oauth = oauthRepository.findByMember(UserContext.getMember());
        oauth.ifPresent(oauthRepository::delete);
    }
}
