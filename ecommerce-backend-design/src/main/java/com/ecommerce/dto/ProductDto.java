package com.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 150)
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Category is required")
    private String category;

    private String description;

    private String imageUrl;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;
}
