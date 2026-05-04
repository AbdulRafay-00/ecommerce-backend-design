package com.ecommerce.controller;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    // ──── Admin Dashboard ────
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.getAllProducts(0).getTotalElements());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("recentProducts", productService.getFeaturedProducts());
        return "admin/dashboard";
    }

    // ──── Show Add Product Form ────
    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", productService.getAllCategories());
        return "admin/product-form";
    }

    // ──── Save New Product ────
    @PostMapping("/products/save")
    public String saveProduct(
            @Valid @ModelAttribute("productDto") ProductDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            return "admin/product-form";
        }
        productService.createProduct(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/admin";
    }

    // ──── Show Edit Product Form ────
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .stock(product.getStock())
                .build();
        model.addAttribute("productDto", dto);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("editMode", true);
        return "admin/product-form";
    }

    // ──── Update Product ────
    @PostMapping("/products/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("productDto") ProductDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            return "admin/product-form";
        }
        productService.updateProduct(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        return "redirect:/admin";
    }

    // ──── Delete Product ────
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted.");
        return "redirect:/admin";
    }
}
