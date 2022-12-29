package com.bside.server.module.auth.service;

import com.bside.server.global.auth.security.JwtValidator;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.module.auth.dto.TokenResponse;
import com.bside.server.module.auth.util.KakaoUserInfo;
import com.bside.server.module.auth.domain.Oauth;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.repository.MemberRepository;
import com.bside.server.module.auth.repository.OauthRepository;
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
    String email = String.valueOf(userInfo.path("kakao_account").get("email")).replace("\"","");

    Optional<Member> member = memberRepository.findByEmailAndIsDeletedIs(email, false);

    // 신규 회원
    if(member.isEmpty()) {
      return memberRepository.saveAndFlush(Member.builder().email(email).isAdmin(false).isDeleted(false).build());
    }

    // 탈퇴 회원
    if(member.get().isDeleted()){
      throw new CustomException(ErrorCode.DELETED_USER);
    }

    return member.get();
  }

  @Transactional
  public TokenResponse createOauth(Member member) {
    final TokenResponse response = jwtValidator.createToken(member.getEmail());
    Oauth oauth = Oauth.builder()
            .accessToken(response.getAccessToken())
            .refreshToken(response.getRefreshToken())
            .type("KAKAO")
            .member(member)
            .build();
    System.out.println(member.getMemberId());
    oauthRepository.save(oauth);
    return response;
  }

}
