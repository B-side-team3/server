package com.bside.server.module.member.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.dto.MemberRequest;
import com.bside.server.module.member.dto.MemberResponse;
import com.bside.server.module.member.repository.MemberRepository;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.repository.MemberRoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final MemberRoutineRepository memberRoutineRepository;

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

  public MemberResponse getRoutineProgress() {
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), 0);
    List<Integer> myPageRoutineCount = new ArrayList<>();
    List<String> myPageRoutineDate = new ArrayList<>();
    if (!ObjectUtils.isEmpty(memberRoutineList)) {
      for (int i = 0; i < memberRoutineList.size(); i++) {
        myPageRoutineCount.add(i);
        myPageRoutineDate.add(memberRoutineList.get(i).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
      }
    }
    return new MemberResponse(myPageRoutineCount, myPageRoutineDate);
  }
}
