package com.trackdelivery.productservice.mapper;

import com.trackdelivery.productservice.dto.ProductDTO;
import com.trackdelivery.productservice.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}