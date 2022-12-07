package com.bside.server.global.login.domain;

import com.bside.server.global.common.entity.Member;
import com.bside.server.global.common.entity.Oauth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthDto {

  private Integer oauthId;

  private Member member;

  private String type;

  private String accessToken;

  private String refreshToken;

  private String isDeleted;

  public OauthDto(Oauth entity) {
    this.oauthId = entity.getOauthId();
    this.member = entity.getMember();
    this.type = entity.getType();
    this.accessToken = entity.getAccessToken();
    this.refreshToken = entity.getRefreshToken();
    this.isDeleted = entity.getIsDeleted();
  }

  public Oauth toEntiity() {
    return Oauth.builder()
        .oauthId(oauthId)
        .member(member)
        .type(type)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .isDeleted(isDeleted)
        .build();
  }
}
