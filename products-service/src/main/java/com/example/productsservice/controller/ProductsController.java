package com.example.productsservice.controller;

import com.example.productsservice.dto.ProductDto;
import com.example.productsservice.dto.UpdateProductDto;
import com.example.productsservice.model.BasicResponse;
import com.example.productsservice.model.Product;
import com.example.productsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @PostMapping("create")
    public BasicResponse<Product> create(
            @RequestBody ProductDto product
    ) {
        return BasicResponse.
                <Product>builder()
                .data(productService.create(product))
                .build();
    }

    @PutMapping("update/{id}")
    public BasicResponse<UpdateProductDto> update(
            @RequestBody UpdateProductDto product,
            @PathVariable int id
    ) {
        return BasicResponse.
                <UpdateProductDto>builder()
                .data(productService.update(product, id))
                .build();
    }

    @GetMapping("all")
    public BasicResponse<Product[]> getAll(
            @RequestParam(name = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        return BasicResponse.
                <Product[]>builder()
                .data(productService.getAll(limit, sort))
                .build();
    }

    @DeleteMapping("{id}")
    public BasicResponse<Product> delete(@PathVariable int id) {
        return BasicResponse.
                <Product>builder()
                .data(productService.delete(id))
                .build();
    }
    @GetMapping("{id}")
    public BasicResponse<Product> getOne(@PathVariable int id) {
        return BasicResponse.
                <Product>builder()
                .data(productService.getOne(id))
                .build();
    }

    @GetMapping("categories")
    public BasicResponse<String[]> categories() {
        return BasicResponse.
                <String[]>builder()
                .data(productService.getCategories())
                .build();
    }

    @GetMapping("category/{category}")
    public BasicResponse<Product[]> productsByCategories(@PathVariable String category) {
        return BasicResponse.
                <Product[]>builder()
                .data(productService.getProductsByCategory(category))
                .build();
    }
}
