package com.example.dummyjson.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductListResponse {
    private List<Product> products;
}
