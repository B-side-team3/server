package com.bside.server.module.member.dto;

import com.bside.server.module.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

  private Integer memberId;

  private String email;

  private String nickname;

  private String imageUrl;

  private Boolean isDeleted;

  private Boolean isAdmin;

  private Boolean isNotification;

  private String notificationToken;

  private LocalDateTime createdDate;

  public MemberResponse(Member member) {
    this.memberId = member.getMemberId();
    this.email = member.getEmail();
    this.nickname = member.getNickname();
    this.imageUrl = member.getImageUrl();
    this.isDeleted = member.isDeleted();
    this.isAdmin = member.isAdmin();
    this.isNotification = member.isNotification();
    this.notificationToken = member.getNotificationToken();
    this.createdDate = member.getCreatedDate();
  }
}
