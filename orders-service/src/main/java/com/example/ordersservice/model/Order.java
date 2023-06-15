package com.example.ordersservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "t_orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
