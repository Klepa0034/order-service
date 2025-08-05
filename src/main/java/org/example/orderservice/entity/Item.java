package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "orderItems")
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;
    @OneToMany(mappedBy = "items", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;
}
