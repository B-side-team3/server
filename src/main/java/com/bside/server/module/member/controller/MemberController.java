package com.bside.server.module.member.controller;

import com.bside.server.module.member.dto.MemberResponse;
import com.bside.server.module.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MemberController {

  private final MemberService memberService;

  @ApiOperation(value = "프로필 조회")
  @GetMapping("/profile")
  public MemberResponse getProfile() {
    return memberService.getProfile();
  }

  @ApiOperation(value = "프로필 수정", httpMethod = "PATCH", produces = "multipart/form-data")
  @PatchMapping(value = "/profile/update")
  public MemberResponse updateProfile(String nickname, @ApiParam(value = "이미지 파일(/jpg, /png)") @RequestPart(required = false) MultipartFile imageFile) {
    return memberService.updateProfile(nickname, imageFile);
  }

  @ApiOperation(value = "프로필 이미지 삭제")
  @PutMapping(value = "/profile/update")
  public void deleteImage() {
    memberService.deleteProfileImage();
  }

  @ApiOperation(value = "회원 탈퇴")
  @PatchMapping("/withdrawal")
  public void withdrawal() {
    memberService.withdrawal();
  }

//  @ApiOperation(value = "루틴 진행 조회")
//  @GetMapping("/routines")
//  public MemberResponse getRoutineProgress() {
//    return memberService.getRoutineProgress();
//  }
}
