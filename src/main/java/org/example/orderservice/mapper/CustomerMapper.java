package org.example.orderservice.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.springframework.stereotype.Service;
@Setter
@Getter
@ToString
@Service
public class CustomerMapper {
    public CustomerDto customerToDto(Customer customer) {
        return new CustomerDto();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return new Customer();
    }
}