package org.example.orderservice.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.entity.Order;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.service.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> all = orderRepository.findAll();
        return all.stream().map(orderMapper::orderToDto).collect(Collectors.toList());
    }

    @Override
    public void findDeleteByIdOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto insertOrder(OrderDto orderDto) {
        Order entityDto = orderMapper.toEntityDto(orderDto);
        Order save = orderRepository.save(entityDto);
        return orderMapper.orderToDto(save);
    }
    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order entityDto = orderMapper.toEntityDto(orderDto);
        orderRepository.save(entityDto);
        return orderMapper.orderToDto(entityDto);
    }
}
