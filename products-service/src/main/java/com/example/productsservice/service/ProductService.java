package com.example.productsservice.service;

import com.example.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${app.products.api.url}")
    String productsApiUrl;
    private final RestTemplate restTemplate;

    public Product[] getAll(int limit, String sort) {
        String query = "?";
        List<String> queries = new ArrayList<>();
        if (limit != 0) queries.add("limit=" + limit);
        if (sort != null) queries.add("sort=" + sort);
        query += String.join(query, "&");
        return Objects.requireNonNull(
                restTemplate.getForObject(productsApiUrl + "/products" + query, Product[].class)
        );
    }

    public Product getOne(int id) {
        return Objects.requireNonNull(restTemplate.getForObject(productsApiUrl + "/products/" + id, Product.class));
    }
}