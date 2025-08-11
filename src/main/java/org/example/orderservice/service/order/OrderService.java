package org.example.orderservice.service.order;

import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> findAllOrders();
    void findDeleteByIdOrder(Long id);
    OrderDto insertOrder(OrderDto orderDto);
    OrderDto updateOrder(OrderDto orderDto);
}
