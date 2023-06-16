package com.example.detailsservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManyProductRes {
   private List<Product> data;
}
