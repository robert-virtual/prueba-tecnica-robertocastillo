package com.example.securityservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "t_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String lastname;
    @Column(columnDefinition = "varchar(320) unique")
    private String email;
    @JsonIgnore
    private String password;
    private LocalDateTime passwordUpdatedAt;
    private LocalDateTime lastLogin = LocalDateTime.now();
    private LocalDateTime createdAt = LocalDateTime.now();
    private int failedLoginAttempts = 0;
    private String status = "active";
}
