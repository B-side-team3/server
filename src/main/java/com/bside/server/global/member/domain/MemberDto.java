package com.bside.server.global.member.domain;

import com.bside.server.global.common.entity.Member;

public class MemberDto {

  private Integer memberId;

  private String email;

  private String nickname;

  private String imageUrl;

  private String isDeleted;

  private Integer isAdmin;

  public MemberDto(Member entity) {
    from(entity);
  }

  public Member to() {
    return Member.builder()
        .memberId(memberId)
        .email(email)
        .nickname(nickname)
        .imageUrl(imageUrl)
        .isDeleted(isDeleted)
        .isAdmin(isAdmin)
        .build();
  }

  public void from(Member entity) {
    memberId = entity.getMemberId();
    email = entity.getEmail();
    nickname = entity.getNickname();
    imageUrl = entity.getImageUrl();
    isDeleted = entity.getIsDeleted();
    isAdmin = entity.getIsAdmin();
  }
}
