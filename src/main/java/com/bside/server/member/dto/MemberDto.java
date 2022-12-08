package com.bside.server.member.dto;

import com.bside.server.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  private Integer memberId;

  private String email;

  private String nickname;

  private String imageUrl;

  private String isDeleted;

  private Integer isAdmin;

  public MemberDto(Member entity) {
    this.memberId = entity.getMemberId();
    this.email = entity.getEmail();
    this.nickname = entity.getNickname();
    this.imageUrl = entity.getImageUrl();
    this.isDeleted = entity.getIsDeleted();
    this.isAdmin = entity.getIsAdmin();
  }

  public Member toEntity() {
    return Member.builder()
        .memberId(memberId)
        .email(email)
        .nickname(nickname)
        .imageUrl(imageUrl)
        .isDeleted(isDeleted)
        .isAdmin(isAdmin)
        .build();
  }
}
