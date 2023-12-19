package com.aos.notification.services;

import com.aos.clients.notification.NotificationRequest;

public interface NotificationService {
    public void send(NotificationRequest notificationRequest);
}
