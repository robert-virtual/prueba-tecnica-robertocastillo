package com.example.productsservice.controller;

import com.example.productsservice.model.BasicResponse;
import com.example.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductsController {

    @Value("${app.products.api.url}")
    String productsApiUrl;
    private final RestTemplate restTemplate;
    @GetMapping("all")
    public BasicResponse<Product[]> getAll(){
        return BasicResponse.
                <Product[]>builder()
                .data(Objects.requireNonNull(restTemplate.getForObject(productsApiUrl + "/products", Product[].class)))
                .build();
    }
}
