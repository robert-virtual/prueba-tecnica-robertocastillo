package com.example.ordersservice.service;

import com.example.ordersservice.dto.*;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ModelMapper modelMapper;
    private final OrdersRepository ordersRepo;

    public OrderDto create(OrderReq orderReq) {
        Order savedOrder = ordersRepo.save(modelMapper.map(orderReq, Order.class));
        return modelMapper.map(savedOrder, OrderDto.class);
    }


    public PagedResponse<OrderDto[]> paged(int page, int size, String customerId) {
        var paged = customerId != null
                ? ordersRepo.findAll(
                Example.of(
                        Order.builder().customerId(UUID.fromString(customerId)).build()
                ), PageRequest.of(page, size)
        )
                : ordersRepo.findAll(PageRequest.of(page, size));
        paged.getTotalPages();
        paged.getTotalElements();
        return PagedResponse
                .<OrderDto[]>builder()
                .data(modelMapper.map(paged.stream().toArray(), OrderDto[].class))
                .page(page)
                .size(size)
                .totalPages(paged.getTotalPages())
                .totalElements(paged.getTotalElements())
                .build();
    }

    public OrderDto getOne(String id) {
        Order order = ordersRepo.findById(UUID.fromString(id)).orElseThrow();
        return modelMapper.map(order, OrderDto.class);
    }

    public Boolean update(PaymentReq paymentReq) {
        Optional<Order> order = ordersRepo.findById(UUID.fromString(paymentReq.getOrderId()));
        if (order.isEmpty()) {
            return false;
        }
        Order updated = order.map(x -> {
            x.setStatus(Order.PAID);
            return ordersRepo.save(x);
        }).get();
        return Objects.equals(updated.getStatus(), Order.PAID);
    }
}
