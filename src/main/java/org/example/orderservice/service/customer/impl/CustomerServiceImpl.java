package org.example.orderservice.service.customer.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.example.orderservice.mapper.CustomerMapper;
import org.example.orderservice.repository.CustomerRepository;
import org.example.orderservice.service.customer.CustomerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> findAllCustomer() {
        List<Customer> all = customerRepository.findAll();

        return all
                .stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void findDeleteByIdCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CustomerDto insertCustomer(CustomerDto customerDto) {
        Customer entity = customerMapper.toEntityDto(customerDto);
        System.out.println(entity);
        Customer save = customerRepository.save(entity);
        return customerMapper.customerToDto(save);
    }
    @Override
    public CustomerDto updateCustomer( CustomerDto customerDto) {
        Customer entityDto = customerMapper.toEntityDto(customerDto);
        Customer save = customerRepository.save(entityDto);
        return customerMapper.customerToDto(save);
    }
}
