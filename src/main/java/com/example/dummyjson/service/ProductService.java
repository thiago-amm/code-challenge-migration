package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductListResponse> getAllProducts() {
        return webClient
                .get()
                .uri("/products")
                .retrieve()
                .bodyToMono(ProductListResponse.class);
    }

    public Mono<Product> getProductById(Long id) {
        return webClient
                .get()
                .uri("/products/" + id)
                .retrieve()
                .bodyToMono(Product.class);
    }

}
