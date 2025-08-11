package org.example.orderservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    @GetMapping
    public List<OrderDto> findAllOrder() {
        return orderService.findAllOrders();
    }
    @DeleteMapping("/{id}")
    public void deleteByIdOrder(@PathVariable Long id) {
        orderService.findDeleteByIdOrder(id);
    }
    @PostMapping
    public ResponseEntity<OrderDto> insertOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.insertOrder(orderDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedOrder);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDto1 = orderService.updateOrder(orderDto);
        return ResponseEntity.ok(orderDto1);
    }
}
