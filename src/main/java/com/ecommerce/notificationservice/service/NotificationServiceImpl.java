package com.ecommerce.notificationservice.service;

import com.ecommerce.notificationservice.dto.request.NotificationRequestDTO;
import com.ecommerce.notificationservice.entity.Notification;
import com.ecommerce.notificationservice.enums.Status;
import com.ecommerce.notificationservice.exception.ApiException;
import com.ecommerce.notificationservice.mapper.NotificationObjectMapper;
import com.ecommerce.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final JavaMailSender javaMailSender;

    private final NotificationObjectMapper notificationObjectMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmail(NotificationRequestDTO notificationRequestDTO) {

        Notification notification;
        try {
            notification = notificationObjectMapper.mapToNotification(notificationRequestDTO);
            notificationRepository.save(notification);
            log.info("Notification saved successfully to database");
        } catch (Exception e) {
            log.error("Error saving notification to database: {}", e.getMessage());
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(notificationRequestDTO.getReceiver());
            message.setSubject(notificationRequestDTO.getSubject());
            message.setText(notificationRequestDTO.getMessage());
            javaMailSender.send(message);
            log.info("Email sent successfully");
        } catch (Exception e) {
            log.error("Error sending email: {}", e.getMessage());
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            notification.setStatus(Status.SENT);
            notificationRepository.save(notification);
            log.info("Notification status changed from {} to {} successfully to database", Status.PENDING, Status.SENT);
        } catch (Exception e) {
            log.error("Error changing notification status from {} to {} to database: {}", Status.PENDING, Status.SENT, e.getMessage());
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
