package com.example.securityservice.repository;

import com.example.securityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByEmail(String email);
}
