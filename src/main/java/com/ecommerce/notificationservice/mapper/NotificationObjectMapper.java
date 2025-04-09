package com.ecommerce.notificationservice.mapper;

import com.ecommerce.notificationservice.dto.request.NotificationRequestDTO;
import com.ecommerce.notificationservice.entity.Notification;
import com.ecommerce.notificationservice.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationObjectMapper {

    public Notification mapToNotification(NotificationRequestDTO notificationRequestDTO) {
        return Notification.builder()
                .status(Status.PENDING)
                .receiver(notificationRequestDTO.getReceiver())
                .subject(notificationRequestDTO.getSubject())
                .message(notificationRequestDTO.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
