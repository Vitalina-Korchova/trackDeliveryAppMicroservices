package com.trackdelivery.orderservice.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseOrderByTracking {

    private String customerName;
    private List<String> productsName;
    private double totalAmount;
    private String shippingAddress;
    private String currentLocation;
    private String deliveryStatus;
}
