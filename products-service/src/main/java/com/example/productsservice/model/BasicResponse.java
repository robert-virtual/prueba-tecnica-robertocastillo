package com.example.productsservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicResponse<T>{
    private T data;
}
