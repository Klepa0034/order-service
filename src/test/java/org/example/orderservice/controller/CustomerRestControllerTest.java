package org.example.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.orderservice.controller.rest.CustomerRestController;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.service.customer.CustomerService;
import org.instancio.Instancio;
import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CustomerRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CustomerService customerService;
    @InjectMocks
    private CustomerRestController customerRestController;

    private final ObjectMapper mapper = new ObjectMapper();


    @org.junit.jupiter.api.Test
    @SneakyThrows
    void findAllCustomer() {
        when(customerService.findAllCustomer())
                .thenReturn(List.of(
                        new CustomerDto(1L, "John", new BigDecimal("1212")),
                        new CustomerDto(2L, "Alice", new BigDecimal("232323"))
                ));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"));


    }

    @SneakyThrows
    @org.junit.jupiter.api.Test
    void findDeleteByIdCustomer() {

        Long id = Instancio.create(Long.class);

        mockMvc.perform(delete("/api/customers/{id}", id))
                .andExpect(status().is2xxSuccessful());

        verify(customerService).findDeleteByIdCustomer(id);

    }

    @SneakyThrows
    @org.junit.jupiter.api.Test
    void insertCustomer() {

        CustomerDto customer = Instancio.create(CustomerDto.class);
        CustomerDto customer1 = Instancio.create(CustomerDto.class);
        when(customerService.insertCustomer(any(CustomerDto.class)))
                .thenReturn(customer1);

        mockMvc.perform(post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(customer)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").value(customer1.getId()));

        verify(customerService).insertCustomer(any(CustomerDto.class));

    }
    @SneakyThrows
    @org.junit.jupiter.api.Test
    void updateCustomer() {
        Long id = Instancio.create(Long.class);
        CustomerDto customerDto = Instancio.create(CustomerDto.class);
        CustomerDto customer1 = Instancio.create(CustomerDto.class);
        when(customerService.updateCustomer(any(CustomerDto.class)))
                .thenReturn(customerDto);

        mockMvc.perform(put("/api/customers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(customerDto.getName()))
                .andExpect(jsonPath("$.balance").value(customerDto.getBalance()));

    }
}