package com.bside.server.login.service;

import com.bside.server.global.auth.security.JwtValidator;
import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.login.dto.AuthDto;
import com.bside.server.login.dto.OauthDto;
import com.bside.server.login.util.KakaoUserInfo;
import com.bside.server.member.domain.Member;
import com.bside.server.login.domain.Oauth;
import com.bside.server.member.dto.MemberDto;
import com.bside.server.member.repository.MemberRepository;
import com.bside.server.login.repository.OauthRepository;
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

  public AuthDto login(HttpServletRequest request) {
    AuthDto authDto = new AuthDto();

    JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(request.getHeader("accessToken"));
    JsonNode kakao_account = userInfo.path("kakao_account");
    String email = kakao_account.get("email").toString();

    Member member = memberRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_USER));
    MemberDto memberDto = new MemberDto(member);

    if (member.getIsDeleted() == 0) {
      memberDto.setEmail(email);
      memberDto.setCreatedDate(LocalDateTime.now());
      memberDto.setIsDeleted(1);
      memberDto.setIsAdmin(0);
      memberRepository.save(memberDto.toEntity());
    } else {
      throw new CustomException(ErrorCode.DELETED_USER);
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

    Oauth oauth = oauthRepository.findByAccessTokenAndIsDeleted(accessToken, 1);

    authDto.setAccessToken(accessToken);
    authDto.setRefreshToken(refreshToken);
    authDto.setEmail(email);
    authDto.setType("KAKAO");
    authDto.setAuthResult(true);
    authDto.setAuthResultMessgage("success");
    authDto.setAccessTime(oauth.getCreatedDate());

    return authDto;
  }
}
