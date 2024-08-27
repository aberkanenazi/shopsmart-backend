package com.aberkane.shopsmart_backend.service;

import com.aberkane.shopsmart_backend.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(Product productDto);
    void updateProduct(Long id, Product productDto);
    void deleteProduct(Long id);
}
