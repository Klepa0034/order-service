package org.example.orderservice.controller.customer.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.orderservice.controller.rest.ItemRestController;
import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.service.item.ItemService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRestController.class)
class ItemRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ItemService itemService;
    @MockitoSpyBean
    private ItemRestController itemRestController;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @SneakyThrows
    public void getItems() {

        List<ItemDto> itemDtos = List.of(Instancio.create(ItemDto.class), Instancio.create(ItemDto.class));
        when(itemService.findAllItems()).thenReturn(itemDtos);


        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(itemDtos.size()))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());

        verify(itemService).findAllItems();

    }

    @Test
    @SneakyThrows
    public void findDeleteByIdItem() {
        Long id = Instancio.create(Long.class);

        mockMvc.perform(delete("/api/customers/{id}", id))
                .andExpect(status().is2xxSuccessful());

        verify(itemService).findDeleteByIdItem(id);
    }

    @Test
    @SneakyThrows
    public void insertItem() {
        ItemDto itemDto = Instancio.create(ItemDto.class);
        when(itemService.insertItem(any(ItemDto.class))).thenReturn(itemDto);
        String content = mapper.writeValueAsString(itemDto);

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(jsonPath("$").exists())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(itemDto.getId()));

        verify(itemService).insertItem(any(ItemDto.class));
        verify(itemRestController).insertItem(any(ItemDto.class));
    }

    @Test
    @SneakyThrows
    public void updateItem() {
        ItemDto itemDto = Instancio.create(ItemDto.class);
        Long id = Instancio.create(Long.class);
        when(itemService.updateItem(any(ItemDto.class))).thenReturn(itemDto);

        String content = mapper.writeValueAsString(itemDto);
        mockMvc.perform(put("/api/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(itemDto.getName()))
                .andExpect(jsonPath("$.balance").value(itemDto.getCost()));

        verify(itemService).updateItem(any(ItemDto.class));

    }

    // @SneakyThrows
    //    @org.junit.jupiter.api.Test
    //    void updateCustomer() {
    //        Long id = Instancio.create(Long.class);
    //        CustomerDto customerDto = Instancio.create(CustomerDto.class);
    //        CustomerDto customer1 = Instancio.create(CustomerDto.class);
    //        when(customerService.updateCustomer(any(CustomerDto.class)))
    //                .thenReturn(customerDto);
    //
    //        mockMvc.perform(put("/api/customers/{id}", id)
    //                        .contentType(MediaType.APPLICATION_JSON)
    //                        .content(mapper.writeValueAsString(customerDto)))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.id").value(id))
    //                .andExpect(jsonPath("$.name").value(customerDto.getName()))
    //                .andExpect(jsonPath("$.balance").value(customerDto.getBalance()));
    //
    //    }

}