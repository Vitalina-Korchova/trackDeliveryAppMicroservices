package com.trackdelivery.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "Customer name must not be empty")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty
    private String phone;

}