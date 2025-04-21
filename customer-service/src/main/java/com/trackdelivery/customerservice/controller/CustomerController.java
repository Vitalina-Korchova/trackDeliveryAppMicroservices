package com.trackdelivery.customerservice.controller;

import com.trackdelivery.customerservice.dto.CustomerDTO;
import com.trackdelivery.customerservice.mapper.CustomerMapper;
import com.trackdelivery.customerservice.model.Customer;
import com.trackdelivery.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id) {
        Customer customer = customerService.findById(id);
        return customerMapper.toDto(customer);
    }

    //для передачі даних
    @GetMapping("/name/{name}")
    public CustomerDTO getCustomerByName(@PathVariable String name) {
        Customer customer = customerService.findByName(name);
        return customerMapper.toDto(customer);
    }

    //для мікросервісів
    @GetMapping("/connection-micro/name/{name}")
    public Customer getCustomerFullObjectByName(@PathVariable String name) {
        return customerService.findByName(name);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
    }

}