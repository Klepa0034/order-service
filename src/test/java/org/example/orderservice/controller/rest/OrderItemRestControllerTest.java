package org.example.orderservice.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.orderservice.dto.orderitem.OrderItemDto;
import org.example.orderservice.service.orderitem.OrderItemService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderItemRestController.class)
class OrderItemRestControllerTest {
    @MockitoBean
    private OrderItemService orderItemService;
    @Autowired
    private MockMvc mockMvc;
    @MockitoSpyBean
    private OrderItemRestController orderRestController;
    @Autowired
    private ObjectMapper mapper;


    @Test
    @SneakyThrows
    void getOrderItems() {
        List<OrderItemDto> orderItems = Instancio.ofList(OrderItemDto.class).size(2).create();
        String jsonContent = mapper.writeValueAsString(orderItems);
        when(orderItemService.findAllOrderItems()).thenReturn(orderItems);

        mockMvc.perform(get("/api/order-items")).andExpect(status().isOk())
                .andExpect(content().json(jsonContent))
                .andExpect(jsonPath("$.length()").value(orderItems.size()))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());

        verify(orderItemService).findAllOrderItems();
    }

    @Test
    @SneakyThrows
    void findDeleteByIdOrderItem() {
        Long id = Instancio.create(Long.class);

        mockMvc.perform(delete("/api/order-items/{id}",id)).andExpect(status().is2xxSuccessful());

        verify(orderItemService).findDeleteByIdOrderItem(id);
    }

//    @Test
//    @SneakyThrows
//    void insertOrderItem() {
//        OrderItemDto orderItemDto = Instancio.create(OrderItemDto.class);
//        String content = mapper.writeValueAsString(orderItemDto);
//        when(orderItemService.insertOrderItem(any(OrderItemDto.class))).thenReturn(orderItemDto);
//
//        mockMvc.perform(post("/api/order-items").contentType(MediaType.APPLICATION_JSON).content(content))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").exists());
//
//        verify(orderItemService).insertOrderItem(orderItemDto);
//    }
//
//    @Test
//    @SneakyThrows
//    void updateOrderItem() {
//        OrderItemDto orderItemDto = Instancio.create(OrderItemDto.class);
//        Long id = Instancio.create(Long.class);
//        when(orderItemService.updateOrderItem(any(OrderItemDto.class))).thenReturn(orderItemDto);
//        String content = mapper.writeValueAsString(OrderItemDto.class);
//
//        mockMvc.perform(put("/api/order-items/{id}",id).contentType(MediaType.APPLICATION_JSON).content(content))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.itemId").value(orderItemDto.getItemId()))
//                .andExpect(jsonPath("$.orderId").value(orderItemDto.getOrderId()));
//
//    }
}