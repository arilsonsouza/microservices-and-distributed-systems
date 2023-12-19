package com.aos.notification.services.impl;

import com.aos.clients.notification.NotificationRequest;
import com.aos.notification.domain.model.Notification;
import com.aos.notification.domain.repository.NotificationRepository;
import com.aos.notification.services.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("Amigoscode")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()

        );
    }
}
