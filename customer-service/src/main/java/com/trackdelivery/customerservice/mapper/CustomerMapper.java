package com.trackdelivery.customerservice.mapper;

import com.trackdelivery.customerservice.dto.CustomerDTO;
import com.trackdelivery.customerservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}