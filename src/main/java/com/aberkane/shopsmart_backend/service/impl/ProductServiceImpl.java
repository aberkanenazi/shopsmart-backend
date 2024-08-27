package com.aberkane.shopsmart_backend.service.impl;

import com.aberkane.shopsmart_backend.model.Product;
import com.aberkane.shopsmart_backend.repository.ProductRepository;
import com.aberkane.shopsmart_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void createProduct(Product productDto) {
        productRepository.save(productDto);
    }

    @Override
    public void updateProduct(Long id, Product productDto) {
        productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.getName());
                    product.setPrice(productDto.getPrice());
                    product.setCategory(productDto.getCategory());
                    product.setQuantity(productDto.getQuantity());
                    product.setDescription(productDto.getDescription());
                    product.setPrice(productDto.getPrice());
                    product.setIsActive(productDto.getIsActive());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
