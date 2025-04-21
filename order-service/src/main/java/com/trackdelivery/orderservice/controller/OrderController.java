package com.trackdelivery.orderservice.controller;

import com.trackdelivery.orderservice.dto.OrderDTO;
import com.trackdelivery.orderservice.mapper.OrderMapper;
import com.trackdelivery.orderservice.model.Order;
import com.trackdelivery.orderservice.response.ResponseOrderByTracking;
import com.trackdelivery.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        List<OrderDTO> orderDTOs = orders.stream().map(order -> new OrderDTO(
                order.getCustomerId(),
                order.getProductsId(),
                order.getTotalAmount(),
                order.getShippingAddress(),
                order.getDeliveryId()
        )).toList();

        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ResponseOrderByTracking> getOrderByTracking(@PathVariable String trackingNumber) {
        ResponseOrderByTracking response = orderService.getOrderInfoByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);

        OrderDTO orderDTO = new OrderDTO(
                order.getCustomerId(),
                order.getProductsId(),
                order.getTotalAmount(),
                order.getShippingAddress(),
                order.getDeliveryId()
        );

        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.updateOrder(id, orderDTO);
        OrderDTO updatedDTO = new OrderDTO(
                updatedOrder.getCustomerId(),
                updatedOrder.getProductsId(),
                updatedOrder.getTotalAmount(),
                updatedOrder.getShippingAddress(),
                updatedOrder.getDeliveryId()
        );
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}