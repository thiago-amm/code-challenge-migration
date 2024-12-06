package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import com.example.dummyjson.fixture.ProductFixture;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        ProductListResponse mockResponse = new ProductListResponse(ProductFixture.validProductList);

        when(productService.getAllProducts())
                .thenReturn(Mono.just(mockResponse));

        // Executa o teste
        webTestClient.get()
                .uri("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.products[0].id").isEqualTo(1)
                .jsonPath("$.products[0].title").isEqualTo("Product 1")
                .jsonPath("$.products[1].id").isEqualTo(2)
                .jsonPath("$.products[1].title").isEqualTo("Product 2");

        // Verifica se o serviço foi chamado
        verify(productService).getAllProducts();
    }

    @Test
    void testGetProductById() {
        // Mock da resposta
        Product mockProduct = ProductFixture.validProduct1;

        when(productService.getProductById(1L)).thenReturn(Mono.just(mockProduct));

        // Executa o teste
        webTestClient.get()
                .uri("/api/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.title").isEqualTo("Product 1");

        // Verifica se o serviço foi chamado
        verify(productService).getProductById(1L);
    }
}
