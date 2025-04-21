package com.trackdelivery.customerservice.service;

import com.trackdelivery.customerservice.dto.CustomerDTO;
import com.trackdelivery.customerservice.mapper.CustomerMapper;
import com.trackdelivery.customerservice.model.Customer;
import com.trackdelivery.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerRepository.save(customer);
    }

    public Customer update(String id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());

        return customerRepository.save(existingCustomer);
    }

    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }
}