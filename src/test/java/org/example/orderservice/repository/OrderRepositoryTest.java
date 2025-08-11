package org.example.orderservice.repository;

import org.example.orderservice.entity.Customer;
import org.example.orderservice.entity.Order;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    void shouldSaveOrder() {
        List<Order> all = orderRepository.findAll();

        assertThat(all.size()).isEqualTo(3);
    }
    @Test
    void shouldDeleteOrder() {
        orderRepository.deleteById(3L);

        List<Order> all = orderRepository.findAll();
        System.out.println(all);
       assertThat(all.size()).isEqualTo(2);
    }


    @Test
    void shouldUpdateOrder() {
        Customer customer = Instancio.of(Customer.class).set(field(Customer::getId), 1L).create();
        Order order = Instancio.of(Order.class).set(field(Order::getId),3L)
                .set(field(Order::getCustomer),customer)
                .ignore(field(Order::getOrderItems))
                .create();
        List<Order> all = orderRepository.findAll();

        orderRepository.save(order);
        Optional<Order> byId = orderRepository.findById(1L);

        System.out.println(all);
        System.out.println(byId);

    }
    @Test
    void shouldInsertOrder() {
        Customer customer = Instancio.of(Customer.class).set(field(Customer::getId), 1L).create();
        Order order = Instancio.of(Order.class)
                .set(field(Order::getCustomer),customer)
                .ignore(field(Order::getOrderItems))
                .ignore(field(Order::getId)).create();

        Order save = orderRepository.save(order);
        Optional<Order> byId = orderRepository.findById(save.getId());

        assertThat(byId.isPresent()).isTrue();
    }
}