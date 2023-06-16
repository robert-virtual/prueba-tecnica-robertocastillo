package com.example.productsservice.dto;

import lombok.Data;

@Data
public class UpdateProductDto {
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}
