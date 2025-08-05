package org.example.orderservice.service;

import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> AllCustomer();
    void DeleteCustomer(Long id);
    CustomerDto InsertUpdateCustomer(CustomerDto customerDto);
//    CustomerDto InsertCustomer(CustomerDto customerDto);
}
