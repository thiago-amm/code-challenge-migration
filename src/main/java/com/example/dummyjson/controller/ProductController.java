package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductListResponse;
import com.example.dummyjson.service.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Deprecated
    @GetMapping("/deprecated")
    public List<Product> getAllProductsDeprecated() {
        return productService.getAllProductsDeprecated();
    }

    @Deprecated
    @GetMapping("/deprecated/{id}")
    public Product getProductByIdDeprecated(@PathVariable @NotNull Long id) {
        return productService.getProductByIdDeprecated(id);
    }

    /**
     * Busca 30 produtos cadastrados.
     *
     * @return Lista contendo 30 produtos
     */
    @GetMapping
    public Mono<ProductListResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Busca um produto cadastrado pelo ID.
     *
     * @param id código identificador do produto
     * @return Produto
     */
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable @NotNull Long id) {
        return productService.getProductById(id);
    }

}
