package com.aberkane.shopsmart_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aberkane.shopsmart_backend.model.Product;
import com.aberkane.shopsmart_backend.model.dto.ProductDto;
import com.aberkane.shopsmart_backend.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServiceImpl productService;

    private final ModelMapper modelMapper;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = products.stream().map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return ResponseEntity.ok(productDto);
    }

}
