package org.example.orderservice.service.orderitem.impl;

import org.example.orderservice.dto.orderitem.OrderItemDto;
import org.example.orderservice.entity.OrderItem;
import org.example.orderservice.mapper.OrderItemMapper;
import org.example.orderservice.repository.OrderItemRepository;
import org.example.orderservice.service.orderitem.OrderItemService;
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
class OrderItemServiceImplTest {
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private OrderItemMapper orderItemMapper;
    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Test
    @DisplayName("findAllOrderItems")
    void findAllOrderItems() {
        OrderItem orderItem = Instancio.create(OrderItem.class);
        OrderItem orderItem1 = Instancio.create(OrderItem.class);
        List<OrderItem> orderItem2 = List.of(orderItem, orderItem1);
        OrderItemDto orderItemDto = Instancio.create(OrderItemDto.class);
        OrderItemDto orderItemDto1 = Instancio.create(OrderItemDto.class);
        when(orderItemRepository.findAll()).thenReturn(List.of(orderItem, orderItem1));
        when(orderItemMapper.orderItemToDto(orderItem)).thenReturn(orderItemDto);
        when(orderItemMapper.orderItemToDto(orderItem1)).thenReturn(orderItemDto1);

        orderItemService.findAllOrderItems();

        verify(orderItemRepository).findAll();
    }
    @Test
    void findDeleteByIdOrderItem() {
        Long id = Instancio.create(Long.class);

        orderItemService.findDeleteByIdOrderItem(id);

        verify(orderItemRepository).deleteById(id);
    }
    @Test
    void insertOrderItem() {
        OrderItem orderItem = Instancio.create(OrderItem.class);
        OrderItemDto orderItemDto = Instancio.create(OrderItemDto.class);
        when(orderItemMapper.orderItemToDto(orderItem)).thenReturn(orderItemDto);
        when(orderItemMapper.toEntityDto(orderItemDto)).thenReturn(orderItem);
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        orderItemService.insertOrderItem(orderItemDto);

        verify(orderItemRepository).save(orderItem);
    }
    @Test
    void updateOrderItem() {
        OrderItem orderItem = Instancio.create(OrderItem.class);
        OrderItemDto orderItemDto = Instancio.create(OrderItemDto.class);
        when(orderItemMapper.orderItemToDto(orderItem)).thenReturn(orderItemDto);
        when(orderItemMapper.toEntityDto(orderItemDto)).thenReturn(orderItem);
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        orderItemService.insertOrderItem(orderItemDto);

        verify(orderItemRepository).save(orderItem);
    }

}