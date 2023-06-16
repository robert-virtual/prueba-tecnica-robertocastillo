package com.example.detailsservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ManyDetails {
    private String orderId;
    private List<DetailReq> details;
}
