package com.bside.server.module.notification.controller;

import com.bside.server.module.notification.dto.NotificationToken;
import com.bside.server.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PatchMapping
    public void updateNotificationToken(@RequestBody NotificationToken notificationToken) {
        notificationService.updateNotificationToken(notificationToken);
    }
}
