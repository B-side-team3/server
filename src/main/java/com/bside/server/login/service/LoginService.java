package com.bside.server.login.service;

import com.bside.server.global.auth.security.JwtValidator;
import com.bside.server.login.domain.AuthModel;
import com.bside.server.login.domain.OauthDto;
import com.bside.server.login.util.KakaoUserInfo;
import com.bside.server.member.domain.Member;
import com.bside.server.member.domain.Oauth;
import com.bside.server.member.dto.MemberDto;
import com.bside.server.member.repository.MemberRepository;
import com.bside.server.member.repository.OauthRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoginService {

  private final MemberRepository memberRepository;
  private final OauthRepository oauthRepository;
  private final JwtValidator jwtValidator;

  public AuthModel login(HttpServletRequest request) throws Exception {
    String email = new String();
    AuthModel authModel = new AuthModel();

    JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(request.getHeader("accessToken"));
    JsonNode kakao_account = userInfo.path("kakao_account");
    email = kakao_account.get("email").toString();

    Member member = memberRepository.findByEmail(email);
    MemberDto memberDto = new MemberDto(member);

    if (member == null || member.getIsDeleted() == 0) {
      memberDto.setEmail(email);
      memberDto.setCreatedDate(LocalDateTime.now());
      memberDto.setIsDeleted(1);
      memberDto.setIsAdmin(0);
      memberRepository.save(memberDto.toEntity());
    }

    OauthDto oauthDto = new OauthDto();
    oauthDto.setMember(memberDto);

    final String accessToken = jwtValidator.createToken(memberDto);
    final String refreshToken = jwtValidator.createRefreshToken(memberDto);

    oauthDto.setAccessToken(accessToken);
    oauthDto.setRefreshToken(refreshToken);
    oauthDto.setType("KAKAO");
    oauthDto.setIsDeleted(1);
    oauthRepository.save(oauthDto.toEntity());
    oauthRepository.flush();

    Oauth oauth = new Oauth();
    oauth = oauthRepository.findByAccessTokenAndIsDeleted(accessToken, 1);

    authModel.setAccessToken(accessToken);
    authModel.setRefreshToken(refreshToken);
    authModel.setEmail(email);
    authModel.setType("KAKAO");
    authModel.setAuthResult(true);
    authModel.setAuthResultMessgage("success");
    authModel.setAccessTime(oauth.getCreatedDate());

    return authModel;
  }
}
