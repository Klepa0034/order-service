package org.example.orderservice.service.orderitem;

import org.example.orderservice.dto.orderitem.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAllOrderItems();
    void findDeleteByIdOrderItem(Long id);
    OrderItemDto insertOrderItem(OrderItemDto orderItemDto);
    OrderItemDto updateOrderItem(OrderItemDto orderItemDto);
}
