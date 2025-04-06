package com.ecommerce.notificationservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationRequestDTO {

    @NotNull(message = "This field is required")
    @NotBlank(message = "This field is required")
    @Email(message = "Please enter valid email")
    private String receiver;

    @NotNull(message = "This field is required")
    @NotBlank(message = "This field is required")
    private String subject;

    @NotNull(message = "This field is required")
    @NotBlank(message = "This field is required")
    private String message;

}
