package com.aos.notification.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aos.clients.notification.NotificationRequest;
import com.aos.notification.services.NotificationService;

@RestController
@RequestMapping("api/v1/notification")
@Slf4j
public class NotificationController {
  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping
  public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
    log.info("New notificaion. {}", notificationRequest);
    notificationService.send(notificationRequest);
  }
}
