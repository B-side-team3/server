package com.bside.server.module.member.dto;

import com.bside.server.module.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

  private Integer memberId;

  private String email;

  private String nickname;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime createdDate;

  private List<Integer> routineCount;

  private List<String> routineDate;

  public MemberResponse(Member member) {
    this.memberId = member.getMemberId();
    this.email = member.getEmail();
    this.nickname = member.getNickname();
    this.createdDate = member.getCreatedDate();
  }

  public MemberResponse(List<Integer> myPageRoutine, List<String> myPageRoutineDate) {
    this.routineCount = myPageRoutine.stream().collect(Collectors.toList());
    this.routineDate = myPageRoutineDate.stream().collect(Collectors.toList());
  }
}
