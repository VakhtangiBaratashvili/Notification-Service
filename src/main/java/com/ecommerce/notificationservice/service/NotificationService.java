package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.NotificationRequestDTO;

public interface NotificationService {
    void sendEmail(NotificationRequestDTO notificationRequestDTO);
}
