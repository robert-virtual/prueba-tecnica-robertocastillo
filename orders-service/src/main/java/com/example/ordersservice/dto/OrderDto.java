package com.example.ordersservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private String shipAddress;
    private LocalDateTime shippedDate;
    private LocalDateTime shipVia;
    private String shipCity;
    private String shipCountry;
    private String shipPostalCode;
    private LocalDateTime createdAt = LocalDateTime.now();
}
