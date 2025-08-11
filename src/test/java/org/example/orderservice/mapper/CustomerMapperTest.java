package org.example.orderservice.mapper;

import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper mapper =Mappers.getMapper(CustomerMapper.class);

    @Test
    @DisplayName("Преоброзвание")
    void shouldCustomerToDto() {
        Customer customer = Instancio.of(Customer.class).set(field(Customer::getId), 1L)
                .set(field(Customer::getName), "Pidorasina")
                .set(field(Customer::getBalance), new BigDecimal("500"))
                .create();

        CustomerDto customerDto = mapper.customerToDto(customer);

        assertThat(customerDto)
                .usingRecursiveComparison()
                .isEqualTo(customer);

    }

    @Test
    @DisplayName("Возвращение")
    void shouldToEntityDto() {
        Customer customer = Instancio.of(Customer.class).set(field(Customer::getId), 1L)
                .set(field(Customer::getName), "Pidorasina")
                .set(field(Customer::getBalance), new BigDecimal("500"))
                .create();

        CustomerDto customerDto = mapper.customerToDto(customer);
        assertThat(customerDto).usingRecursiveComparison().ignoringFields("id").isEqualTo(customer);
    }
}