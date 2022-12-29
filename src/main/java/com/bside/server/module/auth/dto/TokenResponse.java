package com.bside.server.module.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
  private String accessToken;
  private String refreshToken;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Builder.Default
  private LocalDateTime createdTime = LocalDateTime.now();
}
