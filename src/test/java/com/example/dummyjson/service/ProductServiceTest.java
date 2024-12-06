package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import com.example.dummyjson.fixture.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private WebClient webClientMock;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private ResponseSpec responseSpecMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Mock da resposta esperada
        ProductListResponse mockResponse = new ProductListResponse();

        mockResponse.setProducts(ProductFixture.validProductList);

        // Configuração do comportamento dos mocks
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ProductListResponse.class)).thenReturn(Mono.just(mockResponse));

        // Execução do método a ser testado
        Mono<ProductListResponse> result = productService.getAllProducts();

        // Verificação do resultado usando StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getProducts().size() == 2
                                && "Product 1".equals(response.getProducts().get(0).getTitle())
                                && "Product 2".equals(response.getProducts().get(1).getTitle())
                )
                .verifyComplete();

        // Verificação das interações nos mocks
        verify(webClientMock).get();
        verify(requestHeadersUriSpecMock).uri("/products");
        verify(requestHeadersSpecMock).retrieve();
        verify(responseSpecMock).bodyToMono(ProductListResponse.class);
    }

    @Test
    void testGetProductById() {
        // Mock da resposta esperada
        Product mockProduct = ProductFixture.validProduct1;

        // Configuração do comportamento dos mocks
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("/products/1")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(Product.class)).thenReturn(Mono.just(mockProduct));

        // Execução do método a ser testado
        Mono<Product> result = productService.getProductById(1L);

        // Verificação do resultado usando StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(product ->
                        product.getId() == 1L &&
                                "Product 1".equals(product.getTitle())
                                && product.getPrice() == 100
                )
                .verifyComplete();

        // Verificação das interações nos mocks
        verify(webClientMock).get();
        verify(requestHeadersUriSpecMock).uri("/products/1");
        verify(requestHeadersSpecMock).retrieve();
        verify(responseSpecMock).bodyToMono(Product.class);
    }
}
