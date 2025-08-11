package org.example.orderservice.mapper;

import org.example.orderservice.dto.orderitem.OrderItemDto;
import org.example.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto orderItemToDto(OrderItem orderItem);
    OrderItem toEntityDto (OrderItemDto orderItemDto);
}
