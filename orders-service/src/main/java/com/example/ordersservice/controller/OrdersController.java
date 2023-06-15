package com.example.ordersservice.controller;

import com.example.ordersservice.dto.BasicResponse;
import com.example.ordersservice.dto.OrderDto;
import com.example.ordersservice.dto.OrderReq;
import com.example.ordersservice.dto.PagedResponse;
import com.example.ordersservice.service.OrderService;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping("create")
    public BasicResponse<OrderDto> create(@Validated @RequestBody OrderReq orderReq) {
        return BasicResponse
                .<OrderDto>builder()
                .data(orderService.create(orderReq))
                .build();
    }

    @GetMapping("paged")
    public PagedResponse<OrderDto[]> paged(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "customer_id", required = false) String customerId
    ) {
        return orderService.paged(page, size, customerId);
    }

    @GetMapping("{id}")
    public BasicResponse<OrderDto> getOne(@PathVariable String id) {

        return BasicResponse
                .<OrderDto>builder()
                .data(orderService.getOne(id))
                .build();
    }

}
