package com.trackdelivery.orderservice.service;

import com.trackdelivery.orderservice.dto.CustomerDTO;
import com.trackdelivery.orderservice.dto.DeliveryDTO;
import com.trackdelivery.orderservice.dto.OrderDTO;
import com.trackdelivery.orderservice.dto.ProductDTO;
import com.trackdelivery.orderservice.mapper.OrderMapper;
import com.trackdelivery.orderservice.model.Order;
import com.trackdelivery.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RestTemplate restTemplate;

    @Value("${services.customer.url}")
    private String customerServiceUrl;

    @Value("${services.product.url}")
    private String productServiceUrl;

    @Value("${services.delivery.url}")
    private String deliveryServiceUrl;


    public Order save(OrderDTO dto) {

        //  Get Customer by name
        CustomerDTO customer = restTemplate.getForObject(
                customerServiceUrl + "/customers/connection-micro/name/" + dto.getCustomerName(),
                CustomerDTO.class);
        String customerId = customer.getCustomerId();

        // Get Product list by names (POST request)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<String>> productRequest = new HttpEntity<>(dto.getProductsName(), headers);

        ResponseEntity<ProductDTO[]> response = restTemplate.exchange(
                productServiceUrl + "/products/connection-micro/by-names",
                HttpMethod.POST,
                productRequest,
                ProductDTO[].class
        );

        List<String> productIds = Arrays.stream(response.getBody())
                .map(ProductDTO::getProductId)
                .toList();

        // Get Delivery by track number
        DeliveryDTO delivery = restTemplate.getForObject(
                deliveryServiceUrl + "/deliveries/connection-micro/track/" + dto.getTrackingNumber(),
                DeliveryDTO.class);
        String deliveryId = delivery.getDeliveryId();

        Order order = orderMapper.toEntity(dto);
        order.setCustomerId(customerId);
        order.setProductsId(productIds);
        order.setDeliveryId(deliveryId);
        order.setCreatedAt(LocalDateTime.now().toString());
        return orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {

            CustomerDTO customer = restTemplate.getForObject(
                    customerServiceUrl + "/customers/" + order.getCustomerId(),
                    CustomerDTO.class);
            System.out.println(customer);
            String customerName = customer.getName();


            List<String> productNames = order.getProductsId().stream()
                    .map(productId -> {
                        ProductDTO product = restTemplate.getForObject(
                                productServiceUrl + "/products/" + productId,
                                ProductDTO.class
                        );
                        return product.getName();
                    })
                    .toList();

            DeliveryDTO delivery = restTemplate.getForObject(
                    deliveryServiceUrl + "/deliveries/" + order.getDeliveryId(),
                    DeliveryDTO.class);
            System.out.println(delivery);
            String trackNumber = delivery.getTrackingNumber();


            return new Order(
                    order.getOrderId(),
                    customerName,
                    productNames,
                    trackNumber,
                    order.getTotalAmount(),
                    order.getShippingAddress(),
                    order.getCreatedAt()
            );
        }).toList();
    }

    public Order getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));


        CustomerDTO customer = restTemplate.getForObject(
                customerServiceUrl + "/customers/" + order.getCustomerId(),
                CustomerDTO.class);
        String customerName = customer.getName();

        List<String> productNames = order.getProductsId().stream()
                .map(productId -> {
                    ProductDTO product = restTemplate.getForObject(
                            productServiceUrl + "/products/" + productId,
                            ProductDTO.class
                    );
                    return product.getName();
                })
                .toList();


        DeliveryDTO delivery = restTemplate.getForObject(
                deliveryServiceUrl + "/deliveries/" + order.getDeliveryId(),
                DeliveryDTO.class);
        String trackNumber = delivery.getTrackingNumber();

        return new Order(
                order.getOrderId(),
                customerName,
                productNames,
                trackNumber,
                order.getTotalAmount(),
                order.getShippingAddress(),
                order.getCreatedAt()
        );
    }

    public Order updateOrder(String id, OrderDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setTotalAmount(dto.getTotalAmount());
        order.setShippingAddress(dto.getShippingAddress());

        return orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

}