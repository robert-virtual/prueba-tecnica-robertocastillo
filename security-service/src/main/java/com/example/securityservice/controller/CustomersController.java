package com.example.securityservice.controller;

import com.example.securityservice.dto.UserDto;
import com.example.securityservice.model.BasicResponse;
import com.example.securityservice.service.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomersService customersService;

    @GetMapping("all")
    public BasicResponse<List<UserDto>> allCustomers() {
        return BasicResponse.
                <List<UserDto>>builder()
                .data(Arrays.stream(customersService.getAll()).toList())
                .build();
    }


    @GetMapping("{id}")
    public BasicResponse<UserDto> getOne(@PathVariable String id) {
        return BasicResponse.
                <UserDto>builder()
                .data(customersService.getOne(id))
                .build();
    }
}
