package org.example.orderservice.controller.rest;


import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.orderitem.OrderItemDto;
import org.example.orderservice.service.orderitem.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemRestController {
    private final OrderItemService orderItemService;
    @GetMapping
    public List<OrderItemDto> getOrderItems() {
        return orderItemService.findAllOrderItems();
    }
    @DeleteMapping("/{id}")
    public void findDeleteByIdOrderItem(@PathVariable Long id) {
        orderItemService.findDeleteByIdOrderItem(id);
    }
    @PostMapping
    public ResponseEntity<OrderItemDto> insertOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto orderItemDto1 = orderItemService.insertOrderItem(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemDto1);    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto orderItemDto1 = orderItemService.updateOrderItem(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemDto1);
    }
}
