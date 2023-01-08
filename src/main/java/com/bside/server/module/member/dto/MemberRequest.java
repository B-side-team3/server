package com.bside.server.module.member.dto;

import com.bside.server.module.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberRequest {

  private String nickname;

  private String imageUrl;

  public Member toEntity(MemberRequest request) {
    return Member
        .builder()
        .nickname(request.getNickname())
        .imageUrl(request.getImageUrl())
        .build();
  }
}
