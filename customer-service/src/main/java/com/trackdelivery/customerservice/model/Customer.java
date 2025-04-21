package com.trackdelivery.customerservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
@TypeAlias("Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private String customerId;
    private String name;
    private String email;
    private String phone;

}
