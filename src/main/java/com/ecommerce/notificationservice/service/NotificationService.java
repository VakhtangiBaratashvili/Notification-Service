package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.request.NotificationRequestDTO;

public interface NotificationService {
    void sendEmail(NotificationRequestDTO notificationRequestDTO);
}
