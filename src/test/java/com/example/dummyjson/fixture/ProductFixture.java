package com.example.dummyjson.fixture;

import com.example.dummyjson.dto.Product;

import java.util.List;

/**
 * Classe que modela os dados de objetos com dados utilizados
 * em cenários de testes automatizados.
 */
public final class ProductFixture {

    private ProductFixture() {
        throw new UnsupportedOperationException();
    }

    public static final Product validProduct1 = Product.builder()
            .id(1L)
            .title("Product 1")
            .build();

    public static final Product validProduct2 = Product.builder()
            .id(2L)
            .title("Product 2")
            .build();

    public static final List<Product> validProductList = List.of(validProduct1, validProduct2);
}
