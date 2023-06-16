package com.example.paymentsservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class PaymentReq {
  @JsonProperty("order_id")
  private String orderId;
}
