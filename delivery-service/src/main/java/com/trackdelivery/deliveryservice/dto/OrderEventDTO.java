package com.trackdelivery.deliveryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO {
    private String customerName;
    private List<String> productsName;
    private double totalAmount;
    private String shippingAddress;
    private String trackingNumber;
}
