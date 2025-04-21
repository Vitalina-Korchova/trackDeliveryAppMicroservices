package com.trackdelivery.deliveryservice.service;

import com.trackdelivery.deliveryservice.dto.DeliveryDTO;
import com.trackdelivery.deliveryservice.mapper.DeliveryMapper;
import com.trackdelivery.deliveryservice.model.Delivery;
import com.trackdelivery.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(String id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
    }

    public Delivery findByTrackingNumber(String trackingNumber) {
        return deliveryRepository.findByTrackingNumber(trackingNumber);
    }

    public Delivery save(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        delivery.setDeliveryCompany("Nova Poshta");
        delivery.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(2).toString());
        delivery.setTrackingNumber(generateTrackingNumber());
        return deliveryRepository.save(delivery);
    }

    public Delivery update(String id, DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));

        existingDelivery.setCurrentLocation(deliveryDTO.getCurrentLocation());
        existingDelivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

        return deliveryRepository.save(existingDelivery);
    }
    private String generateTrackingNumber() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        return "NP" + uuid;
    }

    public void deleteById(String id) {
        deliveryRepository.deleteById(id);
    }
}