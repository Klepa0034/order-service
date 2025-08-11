package org.example.orderservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;
    @GetMapping
    public List<CustomerDto> findAllCustomer() {
        System.out.println(customerService.findAllCustomer());
        return customerService.findAllCustomer();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findDeleteByIdCustomer(@PathVariable Long id) {
         customerService.findDeleteByIdCustomer(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto insertCustomer(@RequestBody CustomerDto customerDto) {
        System.out.println(customerDto);
        return customerService.insertCustomer(customerDto);
    }
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }
}