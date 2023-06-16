package com.example.securityservice.dto;



public record LoginDto(
        UserDto user,
        String token
) {
}
