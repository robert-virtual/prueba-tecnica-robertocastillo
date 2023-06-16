package com.example.productsservice.controller;

import com.example.productsservice.model.BasicResponse;
import com.example.productsservice.model.Product;
import com.example.productsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("all")
    public BasicResponse<Product[]> getAll(
            @RequestParam(name = "limit",required = false,defaultValue = "0") int limit,
            @RequestParam(name = "sort",required = false) String sort
    ) {
        return BasicResponse.
                <Product[]>builder()
                .data(productService.getAll(limit,sort))
                .build();
    }

    @GetMapping("{id}")
    public BasicResponse<Product> getOne(@PathVariable int id) {
        return BasicResponse.
                <Product>builder()
                .data(productService.getOne(id))
                .build();
    }
}
