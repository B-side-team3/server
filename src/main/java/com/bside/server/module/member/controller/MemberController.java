package com.bside.server.module.member.controller;

import com.bside.server.module.member.dto.MemberRequest;
import com.bside.server.module.member.dto.MemberResponse;
import com.bside.server.module.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MemberController {

  private final MemberService memberService;

  @ApiOperation(value = "프로필 요청")
  @GetMapping("/profile")
  public MemberResponse getProfile() {
    return memberService.getProfile();
  }

  @ApiOperation(value = "프로필 수정")
  @PatchMapping("/profile/update")
  public MemberResponse updateProfile(@RequestBody MemberRequest request) {
    return memberService.updateProfile(request);
  }

  @ApiOperation(value = "회원 탈퇴")
  @PatchMapping("/withdrawal")
  public MemberResponse withdrawal() {
    return memberService.withdrawal();
  }

  /* 구현 예정
  @ApiOperation(value = "루틴 진행 조회")
  @GetMapping("/routines")
  public MemberResponse getRoutineProgress() {
    return memberService.getRoutineProgress();
  }
   */
}
