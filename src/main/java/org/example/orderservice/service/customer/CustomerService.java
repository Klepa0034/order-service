package org.example.orderservice.service.customer;

import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAllCustomer();
    void findDeleteByIdCustomer(Long id);
    CustomerDto insertCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer( CustomerDto customerDto);
}
