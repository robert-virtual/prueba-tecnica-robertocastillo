package com.example.detailsservice.controller;

import com.example.detailsservice.dto.BasicResponse;
import com.example.detailsservice.dto.DetailDto;
import com.example.detailsservice.dto.DetailReq;
import com.example.detailsservice.service.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("details")
@RequiredArgsConstructor
public class DetailsController {
    private final DetailsService detailsService;
    @PostMapping("create")
    public BasicResponse<DetailDto> create(@RequestBody DetailReq detailReq){
        return BasicResponse
                .<DetailDto>builder()
                .data(detailsService.create(detailReq))
                .build();
    }
    @GetMapping("{id}")
    public BasicResponse<DetailDto[]> getByOrderId(@PathVariable String id){
        return BasicResponse
                .<DetailDto[]>builder()
                .data(detailsService.getByOrderId(id))
                .build();
    }
}
