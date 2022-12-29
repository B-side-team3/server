package com.bside.server.module.member.dto;

import com.bside.server.module.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  private Integer memberId;

  private String email;

  private String nickname;

  private String imageUrl;

  private Integer isDeleted;

  private Integer isAdmin;

  private LocalDateTime createdDate;

//  public MemberDto(Member entity) {
//    this.memberId = entity.getMemberId();
//    this.email = entity.getEmail();
//    this.nickname = entity.getNickname();
//    this.imageUrl = entity.getImageUrl();
//    this.isDeleted = entity.isDeleted();
//    this.isAdmin = entity.isAdmin();
//    this.createdDate = entity.getCreatedDate();
//  }

//  public Member toEntity() {
//    return Member.builder()
//        .memberId(memberId)
//        .email(email)
//        .nickname(nickname)
//        .imageUrl(imageUrl)
//        .isDeleted(isDeleted)
//        .isAdmin(isAdmin)
//        .createdDate(createdDate)
//        .build();
//  }
}
