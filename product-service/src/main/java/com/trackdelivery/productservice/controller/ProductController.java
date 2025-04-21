package com.trackdelivery.productservice.controller;

import com.trackdelivery.productservice.dto.ProductDTO;
import com.trackdelivery.productservice.mapper.ProductMapper;
import com.trackdelivery.productservice.model.Product;
import com.trackdelivery.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        return productMapper.toDto(productService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ProductDTO getProductByName(@PathVariable String name) {
        return productMapper.toDto(productService.findByName(name));
    }

    @PostMapping("/connection-micro/by-names")
    public List<Product> getProductsByNames(@RequestBody List<String> names) {
        return productService.findByNames(names);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.save(productDTO);
        return productMapper.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = productService.update(id, productDTO);
        return productMapper.toDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
    }
}