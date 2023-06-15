package com.example.ordersservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagedResponse<T> {
    private T data;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
}
