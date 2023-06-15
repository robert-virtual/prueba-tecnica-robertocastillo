package com.example.detailsservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "t_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID orderId;
    private int productId;
    private int quantity;
    private float price;
    private float discount;
}
