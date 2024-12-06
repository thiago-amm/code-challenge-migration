package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void testGetAllProductsDeprecated() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProductsDeprecated()).thenReturn(products);

        List<Product> result = productController.getAllProductsDeprecated();
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getTitle());
    }

    @Test
    void testGetProductByIdDeprecated() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        when(productService.getProductByIdDeprecated(1L)).thenReturn(product);

        Product result = productController.getProductByIdDeprecated(1L);
        assertEquals("Product 1", result.getTitle());
    }
}
