package org.example.orderservice.repository;

import org.example.orderservice.entity.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomer() {
        List<Customer> all = customerRepository.findAll();
        System.out.println(all);
        assertThat(all.size()).isEqualTo(3).as("liquibase migration has three mock users");
    }

    @Test
    void shouldDeleteCustomer() {
        customerRepository.deleteById(3L);
        List<Customer> all = customerRepository.findAll();

        System.out.println(all);
        assertThat(all.size()).isEqualTo(2).as("liquibase migration has three mock users");


    }
    @Test
    void shouldUpdateCustomer() {
        Customer customer = Instancio.of(Customer.class)
                .set(field(Customer::getId),1L)
                .ignore(field(Customer::getOrders))
                .create();

        List<Customer> all = customerRepository.findAll();//todo Сначала проверят нет ли их в персистендности 1) titi 2323 2) zhopa 23232 получил из бд+сохрнаил в контексте персистендности
       customerRepository.save(customer);//todo 1) random random выполнил запрос изменил в контексте персистендности
       Optional<Customer> updatedCustomer = customerRepository.findById(1L);

        System.out.println(all);
       System.out.println(updatedCustomer.get());
    }
    @Test
    void shouldInsertCustomer(){
        Customer customer = Instancio.of(Customer.class).ignore(field(Customer::getId)).create();

        Customer save = customerRepository.save(customer);
        Optional<Customer> byId = customerRepository.findById(save.getId());

        assertThat(byId.isPresent()).isTrue();

    }

}