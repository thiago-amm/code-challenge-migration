package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final String BASE_URL = "https://dummyjson.com/products";

    private final WebClient webClient;

    private RestTemplate restTemplate;

    public ProductService(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    @Deprecated
    public List<Product> getAllProductsDeprecated() {
        Product[] products = restTemplate.getForObject(BASE_URL, Product[].class);
        return Arrays.asList(products);
    }

    @Deprecated
    public Product getProductByIdDeprecated(Long id) {
        String url = BASE_URL + "/" + id;
        return restTemplate.getForObject(url, Product.class);
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
