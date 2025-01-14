package com.bside.server.module.notification.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.repository.MemberRepository;
import com.bside.server.module.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final MemberRepository memberRepository;

    /**
     * fcm 서버 accessToken 저장
     */
    @Transactional
    public void upsertNotification(NotificationRequest notificationTokenRequest) {
        if( notificationTokenRequest.getToken() == null) {
            throw new CustomException(ErrorCode.EMPTY_NOTIFICATION_TOKEN);
        }

        Member member = memberRepository.findByEmailAndIsDeletedIs(UserContext.getMember().getEmail(), false).orElseThrow(
                () -> new CustomException(ErrorCode.UNKNOWN_USER)
        );

        member.updateNotificationToken(notificationTokenRequest.getToken());
    }

    /**
     * 사용자의 알림 수신 여부 변경
     */
    @Transactional
    public void updateNotificationSetting(NotificationRequest notificationRequest) {
        Member member = memberRepository.findByEmailAndIsDeletedIs(UserContext.getMember().getEmail(), false).orElseThrow(
                () -> new CustomException(ErrorCode.UNKNOWN_USER)
        );

        member.updateIsNotification(notificationRequest.getIsNotification());
    }
}
