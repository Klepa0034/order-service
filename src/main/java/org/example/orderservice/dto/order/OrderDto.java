package org.example.orderservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orderservice.entity.Customer;
import org.example.orderservice.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long quantity;
    private Customer customer;
    private BigDecimal totalCost;
    private List<OrderItem> orderItems;
}
