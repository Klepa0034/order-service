package org.example.orderservice.service.item.impl;

import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.entity.Item;
import org.example.orderservice.mapper.ItemMapper;
import org.example.orderservice.repository.ItemRepository;
import org.example.orderservice.service.item.ItemService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    @Mock
    ItemRepository itemRepository;
    @Mock
    ItemMapper itemMapper;
    @InjectMocks
    ItemServiceImpl itemService;
    @Test
    @Transactional
    @DisplayName("findAllItem")
    void findAllItems() {
        Item item = Instancio.create(Item.class);
        Item item1 = Instancio.create(Item.class);
        List<Item> item2 = List.of(item, item1);
        ItemDto itemDto = Instancio.create(ItemDto.class);
        ItemDto itemDto1 = Instancio.create(ItemDto.class);
        when(itemRepository.findAll()).thenReturn(item2);
        when(itemMapper.itemToDto(item)).thenReturn(itemDto);
        when(itemMapper.itemToDto(item1)).thenReturn(itemDto1);

        itemService.findAllItems();

        verify(itemRepository).findAll();
    }
    @Test
    @DisplayName("findDeleteByIdItem")
    void findDeleteByIdItem() {
        Long id = Instancio.create(Long.class);

        itemService.findDeleteByIdItem(id);

        verify(itemRepository).deleteById(id);
    }

    @Test
    @DisplayName("insertUpdateItem")
    void insertUpdateItem() {

        Item item = Instancio.create(Item.class);
        ItemDto itemDto = Instancio.create(ItemDto.class);
        when(itemMapper.itemToDto(item)).thenReturn(itemDto);
        when(itemMapper.toEntityDto(itemDto)).thenReturn(item);
        when(itemRepository.save(item)).thenReturn(item);


        itemService.insertItem(itemDto);

        verify(itemRepository).save(item);
        verify(itemMapper).itemToDto(item);
        verify(itemMapper).toEntityDto(itemDto);

    }
    @Test
    @DisplayName("updateItem")
    public void updateItem(){
        Item item = Instancio.create(Item.class);
        ItemDto itemDto = Instancio.create(ItemDto.class);
        when(itemMapper.itemToDto(item)).thenReturn(itemDto);
        when(itemMapper.toEntityDto(itemDto)).thenReturn(item);
        when(itemRepository.save(item)).thenReturn(item);

        itemService.updateItem(itemDto);

        verify(itemRepository).save(item);
        verify(itemMapper).itemToDto(item);
        verify(itemMapper).toEntityDto(itemDto);
    }
}