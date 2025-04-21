package com.trackdelivery.deliveryservice.controller;

import com.trackdelivery.deliveryservice.dto.DeliveryDTO;
import com.trackdelivery.deliveryservice.mapper.DeliveryMapper;
import com.trackdelivery.deliveryservice.model.Delivery;
import com.trackdelivery.deliveryservice.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryService.findAll()
                .stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable String id) {
        Delivery delivery = deliveryService.findById(id);
        return deliveryMapper.toDto(delivery);
    }

    @GetMapping("/track/{trackingNumber}")
    public DeliveryDTO getByTrackingNumber(@PathVariable String trackingNumber) {
        Delivery delivery = deliveryService.findByTrackingNumber(trackingNumber);
        return deliveryMapper.toDto(delivery);
    }

    @GetMapping("/connection-micro/track/{trackingNumber}")
    public Delivery getFullObjectByTrackingNumber(@PathVariable String trackingNumber) {
       return deliveryService.findByTrackingNumber(trackingNumber);

    }

    @PostMapping
    public Delivery createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.save(deliveryDTO);
    }

    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable String id, @RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.update(id, deliveryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable String id) {
        deliveryService.deleteById(id);
    }
}