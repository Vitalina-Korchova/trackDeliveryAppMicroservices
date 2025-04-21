package com.trackdelivery.productservice.service;

import com.trackdelivery.productservice.dto.ProductDTO;
import com.trackdelivery.productservice.mapper.ProductMapper;
import com.trackdelivery.productservice.model.Product;
import com.trackdelivery.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }


    public Product save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setCreatedAt(LocalDateTime.now().toString());
        return productRepository.save(product);
    }

    public Product update(String id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setWeight(productDTO.getWeight());
        existingProduct.setStatus(productDTO.getStatus());

        return productRepository.save(existingProduct);
    }


    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByNames(List<String> names) {
        return productRepository.findByNameIn(names);
    }
}