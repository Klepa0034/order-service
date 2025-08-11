package org.example.orderservice.service.orderitem.impl;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.orderitem.OrderItemDto;
import org.example.orderservice.entity.OrderItem;
import org.example.orderservice.mapper.OrderItemMapper;
import org.example.orderservice.repository.OrderItemRepository;
import org.example.orderservice.service.orderitem.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItemDto> findAllOrderItems() {
        List<OrderItem> all = orderItemRepository.findAll();
        return all.
                stream()
                .map(orderItemMapper::orderItemToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void findDeleteByIdOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemDto insertOrderItem(OrderItemDto orderItemDto) {
        OrderItem entityDto = orderItemMapper.toEntityDto(orderItemDto);
        OrderItem save = orderItemRepository.save(entityDto);
        return orderItemMapper.orderItemToDto(save);
    }
    @Override
    public OrderItemDto updateOrderItem(OrderItemDto orderItemDto) {
        OrderItem entityDto = orderItemMapper.toEntityDto(orderItemDto);
        OrderItem save = orderItemRepository.save(entityDto);
        return orderItemMapper.orderItemToDto(save);
    }
}
