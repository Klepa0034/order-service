package org.example.orderservice.service.impl;

import jakarta.transaction.Transactional;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.example.orderservice.mapper.CustomerMapper;
import org.example.orderservice.repository.CustomerRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void allCustomer() {
        Customer customerOne = Instancio.of(Customer.class).create();
        Customer customerTwo = Instancio.of(Customer.class).create();

        when(customerRepository.findAll()).thenReturn(List.of(customerOne, customerTwo));
        when(customerMapper.customerToDto(customerOne)).thenReturn(new CustomerDto(customerOne.getId(), customerOne.getName(), customerOne.getBalance()));
        when(customerMapper.customerToDto(customerTwo)).thenReturn(new CustomerDto(customerTwo.getId(), customerTwo.getName(), customerTwo.getBalance()));

        List<CustomerDto> customerDtos = customerService.AllCustomer();

        assertEquals(customerDtos.size(), 2);


    }

    @Test
    void deleteCustomer() {
        Long customerId = 1L;
        doNothing().when(customerRepository).deleteById(1L);

        customerService.DeleteCustomer(customerId);

        verify(customerRepository, times(1)).deleteById(customerId);


    }

    @Test
    @Transactional
    void insertUpdateCustomer() {
        Customer customer = Instancio.of(Customer.class).create();

        CustomerDto inputDto = Instancio.of(CustomerDto.class)
                .set(field(CustomerDto::getId), customer.getId())
                .set(field(CustomerDto::getName), customer.getName())
                .set(field(CustomerDto::getBalance), customer.getBalance())
                .create();
        Customer savedCustomer = Instancio.of(Customer.class)
                .set(field(Customer::getId), customer.getId())
                .set(field(Customer::getName), customer.getName())
                .set(field(Customer::getBalance), customer.getBalance())
                .create();
        CustomerDto expectedDto = Instancio.of(CustomerDto.class)
                .set(field(CustomerDto::getId), savedCustomer.getId())
                .set(field(CustomerDto::getName), savedCustomer.getName())
                .set(field(CustomerDto::getBalance), savedCustomer.getBalance())
                .create();

        when(customerMapper.customerToDto(savedCustomer)).thenReturn(expectedDto);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.toEntity(inputDto)).thenReturn(customer);

        CustomerDto result = customerService.InsertUpdateCustomer(inputDto);

        assertEquals(expectedDto.getId(), result.getId());
        assertEquals(expectedDto.getName(), result.getName());
        assertEquals(expectedDto.getBalance(), result.getBalance());

        verify(customerRepository).save(customer);


    }
}