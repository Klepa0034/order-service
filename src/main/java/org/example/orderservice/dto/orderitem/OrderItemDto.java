package org.example.orderservice.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orderservice.entity.Item;
import org.example.orderservice.entity.Order;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long Id;
    private Long orderId;
    private Long itemId;
}
