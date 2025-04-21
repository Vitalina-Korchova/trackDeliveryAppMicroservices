package com.trackdelivery.deliveryservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
    @NotEmpty(message = "Current location must not be empty")
    private String currentLocation;
    @NotEmpty(message = "Delivery Status must not be empty")
    private String deliveryStatus;

    private String trackingNumber;
}
