package com.example.productsservice.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

}
