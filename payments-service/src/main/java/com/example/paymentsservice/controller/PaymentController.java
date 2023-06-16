package com.example.paymentsservice.controller;

import com.example.paymentsservice.dto.BasicResponse;
import com.example.paymentsservice.dto.PaymentReq;
import com.example.paymentsservice.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentsService paymentsService;

    @PostMapping("pay")
    public BasicResponse<String> pay(@RequestBody PaymentReq paymentReq) {
        return BasicResponse
                .<String>builder()
                .data(paymentsService.pay(paymentReq))
                .build();
    }
}
