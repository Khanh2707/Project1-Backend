package com.phuckhanh.project1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {
    @NotBlank(message = "ACCOUNT_USERNAME_NOT_NULL")
    String username;
    @NotBlank(message = "ACCOUNT_PASSWORD_NOT_NULL")
    String password;
}
