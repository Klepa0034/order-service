package org.example.orderservice.service.impl;

import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.example.orderservice.mapper.CustomerMapper;
import org.example.orderservice.repository.CustomerRepository;
import org.example.orderservice.service.customer.impl.CustomerServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.instancio.Select.field;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    @Transactional
    @DisplayName("allCustomer")
    public void findAllCustomer() {

        Customer customer = Instancio.create(Customer.class);
        Customer customer1 = Instancio.create(Customer.class);
        List<Customer> customer2 = List.of(customer, customer1);
        CustomerDto customerDto = Instancio.create(CustomerDto.class);
        CustomerDto customerDto1 = Instancio.create(CustomerDto.class);
        when(customerRepository.findAll()).thenReturn(customer2);
        when(customerMapper.customerToDto(customer)).thenReturn(customerDto);
        when(customerMapper.customerToDto(customer1)).thenReturn(customerDto1);

        customerService.findAllCustomer();

        verify(customerRepository).findAll();

    }
    @Test
    @DisplayName("Проверка работы метода Delete")
    public void findDeleteByIdCustomer() {
        Long id = Instancio.create(Long.class);

        customerService.findDeleteByIdCustomer(id);

        verify(customerRepository).deleteById(id);
    }
    @Test
    @Transactional
    @DisplayName("Тест по созданию и обновление")
    void insertCustomer() {
        Customer customer = Instancio.create(Customer.class);
        CustomerDto customerDto = Instancio.create(CustomerDto.class);
        when(customerMapper.toEntityDto(customerDto)).thenReturn(customer);
        when(customerMapper.customerToDto(customer)).thenReturn(customerDto);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.insertCustomer(customerDto);


        verify(customerMapper).toEntityDto(customerDto);
        verify(customerRepository).save(customer);
        verify(customerMapper).customerToDto(customer);

    }
    @Test
    public void updateCustomer() {
        Customer customer = Instancio.create(Customer.class);
        CustomerDto customerDto = Instancio.create(CustomerDto.class);
        when(customerMapper.toEntityDto(customerDto)).thenReturn(customer);
        when(customerMapper.customerToDto(customer)).thenReturn(customerDto);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.updateCustomer(customerDto);

        verify(customerMapper).toEntityDto(customerDto);
        verify(customerRepository).save(customer);
    }
}