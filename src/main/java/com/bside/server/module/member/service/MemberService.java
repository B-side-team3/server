package com.bside.server.module.member.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.AuthenticationException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.auth.service.AuthService;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.dto.MemberResponse;
import com.bside.server.module.member.repository.MemberRepository;
import com.bside.server.module.memberroutine.domain.MemberRoutine;
import com.bside.server.module.memberroutine.repository.MemberRoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

  @Value("${file-path.profileImage:0}")
  private String filePath;

  private final MemberRepository memberRepository;
  private final MemberRoutineRepository memberRoutineRepository;
  private final AuthService authService;

  public MemberResponse getProfile() {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    return new MemberResponse(member);
  }

  @Transactional
  public MemberResponse updateProfile(String nickname, MultipartFile imageFile) {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    member.setNickname(nickname);

    File file = new File(UserContext.getMember().getImageUrl() != null ? UserContext.getMember().getImageUrl() : "");

    if (!ObjectUtils.isEmpty(imageFile)) {
      if (file.exists()) {
        file.delete();
      }

      UUID uuid = UUID.randomUUID();
      String imageFileName = uuid + "_" + imageFile.getOriginalFilename();

      Path imageFilePath = Paths.get(filePath + '/' + imageFileName);

      try {
        Files.write(imageFilePath, imageFile.getBytes());
      } catch (Exception e) {
        e.printStackTrace();
      }

      member.setImageUrl(imageFilePath.toString());
    } else {
      if (file.exists()) {
        file.delete();
      }
      member.setImageUrl(null);
    }

    return new MemberResponse(memberRepository.save(member));
  }

  @Transactional
  public void withdrawal() {
    Member member = memberRepository.findByMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), false).orElseThrow(() -> new AuthenticationException(ErrorCode.UNKNOWN_USER));
    member.setDeleted(true);
    authService.revoke();
    memberRepository.save(member);
  }

  public MemberResponse getRoutineProgress() {
    List<MemberRoutine> memberRoutineList = memberRoutineRepository.findByMemberMemberIdAndIsDeleted(UserContext.getMember().getMemberId(), 0);
    List<Integer> myPageRoutineCount = new ArrayList<>();
    List<String> myPageRoutineDate = new ArrayList<>();
    if (!ObjectUtils.isEmpty(memberRoutineList)) {
      for (int i = 0; i < memberRoutineList.size(); i++) {
        myPageRoutineCount.add(i+1);
        myPageRoutineDate.add(memberRoutineList.get(i).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
      }
    }
    return new MemberResponse(myPageRoutineCount, myPageRoutineDate);
  }
}
