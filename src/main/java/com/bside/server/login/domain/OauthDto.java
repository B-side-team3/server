package com.bside.server.login.domain;

import com.bside.server.member.domain.Oauth;
import com.bside.server.member.dto.MemberDto;
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

  private MemberDto member;

  private String type;

  private String accessToken;

  private String refreshToken;

  private Integer isDeleted;

  public OauthDto(Oauth entity) {
    this.oauthId = entity.getOauthId();
    this.member = new MemberDto(entity.getMember());
    this.type = entity.getType();
    this.accessToken = entity.getAccessToken();
    this.refreshToken = entity.getRefreshToken();
    this.isDeleted = entity.getIsDeleted();
  }

  public Oauth toEntity() {
    return Oauth.builder()
        .oauthId(oauthId)
        .member(member.toEntity())
        .type(type)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .isDeleted(isDeleted)
        .build();
  }
}
