package com.bside.server.module.notification.controller;

import com.bside.server.module.notification.dto.NotificationRequest;
import com.bside.server.module.notification.service.NotificationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @ApiOperation(value = "FCM device 토큰 저장", notes = "토큰을 저장 또는 변경합니다.")
    @ApiImplicitParam(name = "token", value = "fcm device 토큰", required = true)
    @PostMapping("/notification/token")
    public void updateNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.upsertNotification(notificationRequest);
    }

    @ApiOperation(value = "알림 설정 on/off", notes = "사용자의 알림 수신 여부를 설정합니다.")
    @ApiImplicitParam(name = "isNotification", value = "알림 수신 여부", required = true)
    @PutMapping("/notification/setting")
    public void setNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.updateNotificationSetting(notificationRequest);
    }
}
