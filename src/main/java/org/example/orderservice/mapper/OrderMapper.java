package org.example.orderservice.mapper;

import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto orderToDto(Order order);
    Order toEntityDto(OrderDto orderDto);
}
