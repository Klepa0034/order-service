package org.example.orderservice.dto.customer;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerDto {
    private Long id;
    private String name;
    private BigDecimal balance;
}
