package org.example.orderservice.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.orderservice.dto.customer.CustomerDto;
import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.dto.order.OrderDto;
import org.example.orderservice.service.item.ItemService;
import org.example.orderservice.service.order.OrderService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(controllers = OrderRestController.class)
class OrderRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private OrderService orderService;
    @MockitoSpyBean
    private OrderRestController orderRestController;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void findAllOrder() {
        List<OrderDto> orders = Instancio.ofList(OrderDto.class)
                .size(2)
                .create();
        String jsonContent = mapper.writeValueAsString(orders);
        when(orderService.findAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent))
                .andExpect(jsonPath("$.length()").value(orders.size()))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());

        verify(orderService).findAllOrders();

    }

    @Test
    @SneakyThrows
    void deleteByIdOrder() {
        Long id = Instancio.create(Long.class);


        mockMvc.perform(delete("/api/orders/{id}",id))
                .andExpect(status().is2xxSuccessful());

        verify(orderService).findDeleteByIdOrder(id);
    }

    @Test
    @SneakyThrows
    void insertOrder() {

        OrderDto orderDto = Instancio.create(OrderDto.class);
        String content = mapper.writeValueAsString(orderDto);
        when(orderService.insertOrder(any(OrderDto.class))).thenReturn(orderDto);


        mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        verify(orderService).insertOrder(any(OrderDto.class));
    }

    @Test
    @SneakyThrows
    void updateOrder() {
        OrderDto orderDto = Instancio.create(OrderDto.class);
        Long id = Instancio.create(Long.class);
        String content = mapper.writeValueAsString(orderDto);
        when(orderService.updateOrder(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(put("/api/orders/{id}",id).contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.quantity").value(orderDto.getQuantity()))
                .andExpect(jsonPath("$.totalCost").value(orderDto.getTotalCost()));

        verify(orderService).updateOrder(any(OrderDto.class));
    }

}