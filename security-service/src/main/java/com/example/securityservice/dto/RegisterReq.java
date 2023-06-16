package com.example.securityservice.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterReq {
    @NotNull(message = "name is mandatory")
    @NotEmpty(message = "name is mandatory")
    private String name;

    @NotNull(message = "lastname is mandatory")
    @NotEmpty(message = "lastname is mandatory")
    private String lastname;

    @NotNull(message = "email is mandatory")
    @NotEmpty(message = "email is mandatory")
    private String email;

    @NotNull(message = "password is mandatory")
    @NotEmpty(message = "password is mandatory")
    private String password;
}
