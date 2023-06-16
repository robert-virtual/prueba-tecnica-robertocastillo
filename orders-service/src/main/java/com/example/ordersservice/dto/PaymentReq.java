package com.example.ordersservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentReq {
    @JsonProperty("order_id")
    @NotNull(message = "order_id can not be null")
    private String orderId;
}
