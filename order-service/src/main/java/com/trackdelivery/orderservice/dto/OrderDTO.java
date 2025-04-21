package com.trackdelivery.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NotEmpty(message = "Customer name must not be empty")
    private String customerName;
    @Size(min = 1, message = "There should be at least one product")
    private List<String> productsName;
    @Min(1)
    private double totalAmount;
    @NotEmpty(message = "Shipping address must not be empty")
    private String shippingAddress;
    @NotEmpty
    private String trackingNumber;
}