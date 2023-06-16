package com.example.detailsservice.service;

import com.example.detailsservice.dto.DetailDto;
import com.example.detailsservice.dto.DetailReq;
import com.example.detailsservice.model.Detail;
import com.example.detailsservice.repository.DetailsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetailsService {
    private final DetailsRepository detailsRepo;
    private final ModelMapper modelMapper;

    public DetailDto[] getByOrderId(String id) {
        return modelMapper.map(detailsRepo.findAllByOrderId(
                UUID.fromString(id)
        ), DetailDto[].class);
    }

    public DetailDto create(DetailReq detailReq) {
        return modelMapper.map(detailsRepo.save(modelMapper.map(detailReq, Detail.class)), DetailDto.class);
    }

    public DetailDto[] createMany(List<DetailReq> detailReq) {
        var entities = modelMapper.map(detailReq, Detail[].class);
        return modelMapper.map(detailsRepo.saveAll(Arrays.stream(entities).toList()), DetailDto[].class);
    }
}
