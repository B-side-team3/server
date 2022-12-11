package com.bside.server.login.domain;

import com.bside.server.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "oauth")
public class Oauth {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "oauth_id", nullable = false)
  private Integer oauthId;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "access_token")
  private String accessToken;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "type")
  private String type;

  @Column(name = "is_deleted", columnDefinition = "jwt token 삭제 여부")
  private Integer isDeleted;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @CreationTimestamp
  @Column(name = "created_date")
  private LocalDateTime createdDate;
}
