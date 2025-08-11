package org.example.orderservice.repository;

import org.example.orderservice.entity.Item;
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
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void shouldSaveItem() {
        List<Item> all = itemRepository.findAll();

        assertThat(all.size()).isEqualTo(3).as("liquibase migration has three mock users");
    }
    @Test
    void shouldDeleteItem() {
        itemRepository.deleteById(1L);
        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(2).as("liquibase migration has three mock users");;
    }
    @Test
    void shouldUpdateItem() {
        Item item = Instancio.of(Item.class)
                .set(field(Item::getId), 1L)
                .ignore(field(Item::getOrderItems))
                .create();

        List<Item> all = itemRepository.findAll();
        itemRepository.save(item);
        Optional<Item> byId = itemRepository.findById(1L);
        System.out.println(all);
        System.out.println(byId);
    }
    @Test
    void shouldInsertItem() {
        Item item = Instancio.of(Item.class).ignore(field(Item::getId)).create();
        Item save = itemRepository.save(item);
        Optional<Item> byId = itemRepository.findById(save.getId());
        assertThat(byId.isPresent()).isTrue();
    }
}