package com.example.productsservice.controller;

import com.example.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductsController {

    @Value("${app.products.api.url}")
    String productsApiUrl;
    private final RestTemplate restTemplate;
    @GetMapping("all")
    public Product[] getAll(){
        return restTemplate.getForObject(productsApiUrl+"/products", Product[].class);
    }
}
