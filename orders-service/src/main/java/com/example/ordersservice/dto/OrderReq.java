package com.example.ordersservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderReq {
    private UUID customerId;
    private String shipAddress;
    private String shipCity;
    private String shipCountry;
    private String shipPostalCode;
}
