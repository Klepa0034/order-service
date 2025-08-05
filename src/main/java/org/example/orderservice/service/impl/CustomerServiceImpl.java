package org.example.orderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.example.orderservice.mapper.CustomerMapper;
import org.example.orderservice.repository.CustomerRepository;
import org.example.orderservice.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDto customerDto;
    private final CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> AllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void DeleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CustomerDto InsertUpdateCustomer(CustomerDto customerDto) {
        Customer entity = customerMapper.toEntity(customerDto);
        Customer save = customerRepository.save(entity);
        return customerMapper.customerToDto(save);
    }

//    @Override
//    public CustomerDto InsertCustomer(CustomerDto customerDto) {
//        Customer entity = customerMapper.toEntity(customerDto);
//        Customer save = customerRepository.save(entity);
//        return customerMapper.customerToDto(save);
//    }
}
