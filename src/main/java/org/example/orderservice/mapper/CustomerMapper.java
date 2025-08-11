package org.example.orderservice.mapper;


import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.entity.Customer;
import org.hibernate.tool.schema.TargetType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);
    Customer toEntityDto(CustomerDto customerDto);
}
