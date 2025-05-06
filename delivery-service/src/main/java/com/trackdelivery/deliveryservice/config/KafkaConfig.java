package com.trackdelivery.deliveryservice.config;

import com.trackdelivery.deliveryservice.dto.OrderEventDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {
    @KafkaListener(
            topics = "order-topic",
            groupId = "delivery-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenOrder(OrderEventDTO orderEvent) {
        System.out.println("Consumer: received Order for Delivery: " + orderEvent);
    }

}
