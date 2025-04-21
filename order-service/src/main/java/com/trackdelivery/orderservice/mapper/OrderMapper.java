package com.trackdelivery.orderservice.mapper;

import com.trackdelivery.orderservice.dto.OrderDTO;
import com.trackdelivery.orderservice.model.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Order delivery);
    Order toEntity(OrderDTO deliveryDTO);
}