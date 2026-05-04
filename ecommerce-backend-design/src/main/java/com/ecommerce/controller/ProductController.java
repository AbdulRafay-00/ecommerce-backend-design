package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ──── Product Listing Page (/products) ────
    @GetMapping("/products")
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String category,
            Model model) {

        Page<Product> productPage;

        if (category != null && !category.isBlank()) {
            productPage = productService.getProductsByCategory(category, page);
            model.addAttribute("selectedCategory", category);
        } else {
            productPage = productService.getAllProducts(page);
        }

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("categories", productService.getAllCategories());
        return "products/list";
    }

    // ──── Server-side Search ────
    @GetMapping("/search")
    public String searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Product> productPage = productService.searchProducts(query, page);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("searchQuery", query);
        model.addAttribute("categories", productService.getAllCategories());
        return "products/list";
    }

    // ──── Product Detail Page (/products/{id}) ────
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            model.addAttribute("categories", productService.getAllCategories());
            return "products/detail";
        } catch (RuntimeException e) {
            return "redirect:/products";
        }
    }
}
