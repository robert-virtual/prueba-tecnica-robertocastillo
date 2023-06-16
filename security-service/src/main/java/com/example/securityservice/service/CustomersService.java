package com.example.securityservice.service;

import com.example.securityservice.dto.UserDto;
import com.example.securityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomersService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;

    public UserDto[] getAll() {
        return modelMapper.map(userRepo.findAll(), UserDto[].class);
    }

    public UserDto getOne(String id) {
        return modelMapper.map(userRepo.findById(UUID.fromString(id)), UserDto.class);
    }

}
