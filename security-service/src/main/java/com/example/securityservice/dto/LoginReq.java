package com.example.securityservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginReq(
        @NotNull(message = "email is mandatory")
        @NotEmpty(message = "empty email")
        String email,
        @NotNull(message = "password is mandatory")
        @NotEmpty(message = "empty password")
        String password
) {
}
