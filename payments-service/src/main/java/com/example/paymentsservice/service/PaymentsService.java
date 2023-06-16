package com.example.paymentsservice.service;

import com.example.paymentsservice.dto.PaymentReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PaymentsService {
    private final WebClient.Builder webClientBuilder;

    public String pay(PaymentReq paymentReq) {
        WebClient webClient = webClientBuilder.build();
        Boolean res = webClient
                .put()
                .uri("lb://orders-service/orders/update")
                .body(Mono.just(paymentReq), PaymentReq.class)
                .retrieve()
                .bodyToMono(Boolean.class).block();
        return Boolean.TRUE.equals(res) ? "payment received" : "payment error try again later";
    }
}
