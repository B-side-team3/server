package com.bside.server.global.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OAUTH")
public class Oauth {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OAUTH_ID", nullable = false)
  private Integer oauthId;

  @OneToOne
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @Column(name = "ACCESS_TOKEN")
  private String accessToken;

  @Column(name = "REFRESH_TOKEN")
  private String refreshToken;

  @Column(name = "TYPE")
  private String type;

  @Column(name = "IS_DELETE", columnDefinition = "jwt token 삭제 여부")
  private String isDeleted;

  @Builder.Default
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "ACCESS_TIME")
  private LocalDateTime accessTime = LocalDateTime.now();
}
