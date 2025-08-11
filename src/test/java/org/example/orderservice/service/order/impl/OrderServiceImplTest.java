package org.example.orderservice.service.order.impl;

import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.entity.Order;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.repository.OrderRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    @Test
    @DisplayName("findAllOrders")
    void findAllOrders() {
        Order order = Instancio.create(Order.class);
        Order order1 = Instancio.create(Order.class);
        List<Order> order2 = List.of(order, order1);
        OrderDto orderDto = Instancio.create(OrderDto.class);
        OrderDto orderDto1 = Instancio.create(OrderDto.class);
        when(orderRepository.findAll()).thenReturn(order2);
        when(orderMapper.orderToDto(order)).thenReturn(orderDto);
        when(orderMapper.orderToDto(order1)).thenReturn(orderDto1);

        orderService.findAllOrders();

        verify(orderRepository).findAll();
    }
    @Test
    @DisplayName("findDeleteByIdOrder")
    void findDeleteByIdOrder() {
        Long id = Instancio.create(Long.class);

        orderService.findDeleteByIdOrder(id);

        verify(orderRepository).deleteById(id);
    }
    @Test
    @DisplayName("insertOrder")
    void insertOrder() {
        Order order = Instancio.create(Order.class);
        OrderDto orderDto = Instancio.create(OrderDto.class);
        when(orderMapper.orderToDto(order)).thenReturn(orderDto);
        when(orderMapper.toEntityDto(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);


        orderService.insertOrder(orderDto);

        verify(orderRepository).save(order);
    }
    @Test
    @DisplayName("updateOrder")
    void updateOrder(){
        Order order = Instancio.create(Order.class);
        OrderDto orderDto = Instancio.create(OrderDto.class);
        when(orderMapper.orderToDto(order)).thenReturn(orderDto);
        when(orderMapper.toEntityDto(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);


        orderService.insertOrder(orderDto);

        verify(orderRepository).save(order);
    }
}