package com.bside.server.module.notification.controller;

import com.bside.server.module.notification.dto.NotificationRequest;
import com.bside.server.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PatchMapping
    public void updateNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.updateNotification(notificationRequest);
    }
}
