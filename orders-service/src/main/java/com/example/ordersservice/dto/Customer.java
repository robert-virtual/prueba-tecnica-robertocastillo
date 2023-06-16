package com.example.ordersservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Customer {
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private int failedLoginAttempts;
    private String status;
}
