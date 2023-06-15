package com.example.detailsservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DetailDto {
    private UUID id;
    private UUID orderId;
    private int productId;
    private int quantity;
    private float price;
    private float discount;
}
