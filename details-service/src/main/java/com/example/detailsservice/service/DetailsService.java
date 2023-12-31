package com.example.detailsservice.service;

import com.example.detailsservice.dto.*;
import com.example.detailsservice.model.Detail;
import com.example.detailsservice.repository.DetailsRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailsService {
    private final DetailsRepository detailsRepo;
    private final ModelMapper modelMapper;
    private final WebClient.Builder webclientBuilder;

    public DetailDto[] getByOrderId(String id) {
        return modelMapper.map(detailsRepo.findAllByOrderId(
                UUID.fromString(id)
        ), DetailDto[].class);
    }

    public DetailDto create(DetailReq detailReq) {
        WebClient webClient = webclientBuilder.build();
        OrderRes order = webClient
                .get()
                .uri("lb://orders-service/orders/{id}", detailReq.getOrderId())
                .retrieve()
                .bodyToMono(OrderRes.class).block();
        if (order == null) {
            throw new NotFoundException("Order not found");
        }
        log.info(order.getData().getCreatedAt().toString());

        ProductRes product = webClient
                .get()
                .uri("lb://products-service/products/{id}", detailReq.getProductId())
                .retrieve()
                .bodyToMono(ProductRes.class).block();
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        detailReq.setPrice(product.getData().getPrice());
        return modelMapper.map(detailsRepo.save(modelMapper.map(detailReq, Detail.class)), DetailDto.class);
    }

    public DetailDto[] createMany(ManyDetails detailReq) {
        WebClient webClient = webclientBuilder.build();
        OrderRes order = webClient
                .get()
                .uri("lb://orders-service/orders/{id}", detailReq.getOrderId())
                .retrieve()
                .bodyToMono(OrderRes.class).block();
        if (order == null) {
            throw new NotFoundException("Order not found");
        }

        ManyProductRes productsRes = webClient
                .get()
                .uri("lb://products-service/products/all")
                .retrieve()
                .bodyToMono(ManyProductRes.class).block();
        if (productsRes == null) {
            throw new NotFoundException("Products not found");
        }
        List<Product> products = productsRes.getData();
        List<DetailReq> details = detailReq.getDetails().stream().peek(
                detail -> {
                    detail.setOrderId(UUID.fromString(detailReq.getOrderId()));
                    Optional<Product> found = products.stream().filter(product -> product.getId() == detail.getProductId()).findFirst();
                    if (found.isEmpty())
                        throw new NotFoundException("Product with id=%s not found".formatted(detail.getProductId()));
                    detail.setPrice(found.get().getPrice());
                }
        ).toList();
        var entities = modelMapper.map(details, Detail[].class);
        return modelMapper.map(detailsRepo.saveAll(Arrays.stream(entities).toList()), DetailDto[].class);
    }
}
