package com.bside.server.module.member.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.dto.MemberRequest;
import com.bside.server.module.member.dto.MemberResponse;
import com.bside.server.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberResponse getProfile() {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    return new MemberResponse(member);
  }

  @Transactional
  public MemberResponse updateProfile(MemberRequest request) {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    member.setNickname(request.getNickname());
//    member.setImageUrl(); /* todo 프로필 이미지 변경 로직 구현 예정
    return new MemberResponse(memberRepository.save(member));
  }

  @Transactional
  public MemberResponse withdrawal() {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    member.setDeleted(true);
    /* todo 토큰 폐기 로직 구현 예정 */
    return new MemberResponse(memberRepository.save(member));
  }

  /* todo 구현 예정 가입일, 루틴등록날짜, 루틴 횟수 카운트
  public MemberResponse getRoutineProgress() {
    return new MemberResponse();
  }
   */
}
