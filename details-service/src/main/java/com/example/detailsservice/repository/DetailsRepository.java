package com.example.detailsservice.repository;

import com.example.detailsservice.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetailsRepository extends JpaRepository<Detail, UUID> {
}
