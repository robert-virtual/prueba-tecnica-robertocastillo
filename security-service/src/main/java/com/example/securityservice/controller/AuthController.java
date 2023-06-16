package com.example.securityservice.controller;

import com.example.securityservice.dto.LoginDto;
import com.example.securityservice.dto.LoginReq;
import com.example.securityservice.dto.RegisterReq;
import com.example.securityservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("security")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public LoginDto login(@Validated @RequestBody LoginReq loginReq) throws Exception {
       return authService.login(loginReq);
    }

    @PostMapping("register")
    public LoginDto register(@Validated @RequestBody RegisterReq registerReq){
        return authService.register(registerReq);
    }
    @GetMapping("customer-id")
    public String getCustomerId(@RequestHeader("Authorization") String authorization) {
        return authService.getCustomerId(authorization);
    }
}

