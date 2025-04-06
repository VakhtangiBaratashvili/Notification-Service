package com.ecommerce.notificationservice.controller;

import com.ecommerce.notificationservice.dto.request.NotificationRequestDTO;
import com.ecommerce.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rest/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid NotificationRequestDTO notificationRequestDTO) {
        notificationService.sendEmail(notificationRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
