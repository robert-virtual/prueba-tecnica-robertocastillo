package com.example.detailsservice.dto;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

}
