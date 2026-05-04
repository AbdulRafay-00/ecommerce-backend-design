package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${app.pagination.page-size}")
    private int pageSize;

    // ──── Featured products for Home Page ────
    public List<Product> getFeaturedProducts() {
        return productRepository.findTop6ByStockGreaterThanOrderByCreatedAtDesc(0);
    }

    // ──── All products with pagination ────
    public Page<Product> getAllProducts(int page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        return productRepository.findAll(pageable);
    }

    // ──── Search by name or category ────
    public Page<Product> searchProducts(String query, int page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        return productRepository.searchProducts(query, pageable);
    }

    // ──── Filter by category ────
    public Page<Product> getProductsByCategory(String category, int page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        return productRepository.findByCategory(category, pageable);
    }

    // ──── Single product ────
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ──── Get all categories ────
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    // ──── Create product (Admin) ────
    public Product createProduct(ProductDto dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .stock(dto.getStock())
                .build();
        return productRepository.save(product);
    }

    // ──── Update product (Admin) ────
    public Product updateProduct(Long id, ProductDto dto) {
        Product existing = getProductById(id);
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setCategory(dto.getCategory());
        existing.setDescription(dto.getDescription());
        existing.setImageUrl(dto.getImageUrl());
        existing.setStock(dto.getStock());
        return productRepository.save(existing);
    }

    // ──── Delete product (Admin) ────
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
