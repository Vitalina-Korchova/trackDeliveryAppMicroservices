package com.trackdelivery.deliveryservice.mapper;

import com.trackdelivery.deliveryservice.dto.DeliveryDTO;
import com.trackdelivery.deliveryservice.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(target = "trackingNumber", source = "trackingNumber")
    DeliveryDTO toDto(Delivery delivery);
    Delivery toEntity(DeliveryDTO deliveryDTO);
}