package com.example.securityservice.service;

import com.example.securityservice.dto.*;
import com.example.securityservice.exception.BadCredentialsException;
import com.example.securityservice.model.User;
import com.example.securityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginDto register(RegisterReq registerReq) {
        User user = modelMapper.map(registerReq, User.class);
        log.info(user.getPassword());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        User savedUser = userRepo.save(user);
        return new LoginDto(
                modelMapper.map(savedUser, UserDto.class),
                jwtService.generateToken(savedUser)
        );
    }

    public LoginDto login(LoginReq loginReq) throws Exception {
        User user = userRepo.findOneByEmail(
                loginReq.email()
        ).orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        if (!passwordEncoder.matches(
                loginReq.password(),
                user.getPassword()
        )) {
            throw new BadCredentialsException("Bad credentials");
        }
        user.setLastLogin(LocalDateTime.now());
        User savedUser = userRepo.save(user);
        return new LoginDto(
                modelMapper.map(savedUser, UserDto.class),
                jwtService.generateToken(user)
        );
    }

    public String getCustomerId(String authorization) {
        return jwtService.extractUsername(authorization.substring("Bearer ".length()));
    }
}
