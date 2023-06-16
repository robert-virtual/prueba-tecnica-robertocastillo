package com.example.productsservice.service;

import com.example.productsservice.dto.ProductDto;
import com.example.productsservice.dto.UpdateProductDto;
import com.example.productsservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Value("${app.products.api.url}")
    String productsApiUrl;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    public Product[] getAll(int limit, String sort) {
        String query = "?";
        List<String> queries = new ArrayList<>();
        if (limit != 0) queries.add("limit=" + limit);
        if (sort != null) queries.add("sort=" + sort);
        query += String.join("&", queries);
        String url = productsApiUrl + "/products" + query;
        return Objects.requireNonNull(
                restTemplate.getForObject(url, Product[].class)
        );
    }

    public Product getOne(int id) {
        return Objects.requireNonNull(
                restTemplate.getForObject(
                        productsApiUrl + "/products/{id}",
                        Product.class,
                        id
                )
        );
    }

    public String[] getCategories() {
        return Objects.requireNonNull(restTemplate.getForObject(productsApiUrl + "/products/categories", String[].class));
    }

    public Product[] getProductsByCategory(String category) {
        return Objects.requireNonNull(
                restTemplate.getForObject(
                        productsApiUrl + "/products/category/{category}", Product[].class,
                        category
                )
        );
    }

    public Product create(ProductDto product) {
        return Objects.requireNonNull(
                restTemplate.postForObject(
                        productsApiUrl + "/products",
                        product,
                        Product.class
                )
        );
    }

    public UpdateProductDto update(UpdateProductDto product, int id) {
        log.info(product.getCategory());
        ResponseEntity<UpdateProductDto> productRes = restTemplate.exchange(
                productsApiUrl + "/products/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(product),
                UpdateProductDto.class,
                id
        );
//        return modelMapper.map(product, Product.class);
        return productRes.getBody();
    }

    public Product delete(int id) {
        ResponseEntity<Product> response = restTemplate.exchange(
                productsApiUrl + "/products/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(""),
                Product.class,
                id
        );
        return response.getBody();
    }
}
