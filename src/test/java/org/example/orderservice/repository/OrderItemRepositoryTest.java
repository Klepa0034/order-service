package org.example.orderservice.repository;

import org.example.orderservice.entity.OrderItem;
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
class OrderItemRepositoryTest {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    void shouldSaveOrderItemRepository() {
        List<OrderItem> all = orderItemRepository.findAll();
        System.out.println(all);
        assertThat(all.size()).isEqualTo(3).as("liquibase migration has three mock users");
    }

    @Test
    void shouldDeleteOrderItemRepository() {
        orderItemRepository.deleteById(3L);
        List<OrderItem> all = orderItemRepository.findAll();
        System.out.println(all);
        assertThat(all.size()).isEqualTo(3).as("liquibase migration has three mock users");
    }

}