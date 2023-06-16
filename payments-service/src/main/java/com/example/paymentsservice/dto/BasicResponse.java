package com.example.paymentsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicResponse<T>{
    private T data;
}
