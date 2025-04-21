package com.trackdelivery.productservice.repository;

import com.trackdelivery.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
    List<Product> findByNameIn(List<String> names);
}