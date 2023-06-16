package com.example.productsservice.dto;

import com.example.productsservice.model.Rating;
import lombok.Data;

@Data
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
