package com.example.securityservice.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterReq {
    @NotNull(message = "name is mandatory")
    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotNull(message = "lastname is mandatory")
    @NotEmpty(message = "lastname can not be empty")
    private String lastname;

    @NotNull(message = "email is mandatory")
    @NotEmpty(message = "email can not be empty")
    private String email;

    @NotNull(message = "password is mandatory")
    @NotEmpty(message = "password can not be empty")
    private String password;
}
