package com.phuckhanh.project1.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountUpdateRequest {
    String username;
    String password;
    @Min(value = 1, message = "ACCOUNT_AGE_NOT_SMALLER_THAN_1")
    Integer age;
}
