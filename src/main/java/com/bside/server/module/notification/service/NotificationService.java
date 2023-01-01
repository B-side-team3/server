package com.bside.server.module.notification.service;

import com.bside.server.global.error.ErrorCode;
import com.bside.server.global.error.exception.CustomException;
import com.bside.server.global.util.UserContext;
import com.bside.server.module.member.domain.Member;
import com.bside.server.module.member.repository.MemberRepository;
import com.bside.server.module.notification.dto.NotificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationService {

    private final MemberRepository memberRepository;

    @Transactional
    public void updateNotificationToken(NotificationToken notificationToken) {
        Member member = memberRepository.findByEmailAndIsDeletedIs(UserContext.getMember().getEmail(), false).orElseThrow(
                () -> new CustomException(ErrorCode.UNKNOWN_USER)
        );

        boolean isNotification = member.isNotification();
        member.updateIsNotification(!isNotification);
        if(notificationToken.getToken() != null) {
            member.updateNotificationToken(notificationToken.getToken());
        }
    }
}
