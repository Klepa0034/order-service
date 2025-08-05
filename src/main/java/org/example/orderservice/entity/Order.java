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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="quantity")
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(name="total_cost", nullable = false,precision = 10, scale = 2)
    private BigDecimal totalCost;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;

}
