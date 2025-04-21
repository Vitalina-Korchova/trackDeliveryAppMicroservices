package com.trackdelivery.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotEmpty(message = "Product name must not be empty")
    private String name;
    @Min(1)
    @Positive(message = "Price must be positive")
    private double price;
    @Min(5)
    @Positive(message = "Weight must be positive")
    private double weight;
    @NotEmpty(message = "Status must not be empty")
    private String status;
}