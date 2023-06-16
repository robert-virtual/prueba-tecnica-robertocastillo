package com.example.securityservice.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterReq {
    @NotNull(message = "password is mandatory")
    private String name;
    private String lastname;
    private String email;
    private String password;
}
