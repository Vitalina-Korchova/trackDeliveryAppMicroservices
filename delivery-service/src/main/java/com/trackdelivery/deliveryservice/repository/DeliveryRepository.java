package com.trackdelivery.deliveryservice.repository;

import com.trackdelivery.deliveryservice.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Delivery findByTrackingNumber(String trackingNumber);
}