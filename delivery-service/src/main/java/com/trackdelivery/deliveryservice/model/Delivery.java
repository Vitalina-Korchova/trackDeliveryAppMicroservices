package com.trackdelivery.deliveryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Delivery")
@TypeAlias("Delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    private String deliveryId;
    private String deliveryCompany; //вставити нова пошта авто заповнення
    private String estimatedDeliveryDate;//сьогоднішня дата +2 дні
    private String currentLocation;
    private String deliveryStatus;
    private String trackingNumber; //створити рандом циферки і літери
}
