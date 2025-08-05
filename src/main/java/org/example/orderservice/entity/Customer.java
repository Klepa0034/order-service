package org.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "orders")
@Component
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String  name;
    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;
    @OneToMany(mappedBy ="customer",cascade = CascadeType.REMOVE)
    private List<Order> orders;


}
