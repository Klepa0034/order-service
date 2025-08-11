package org.example.orderservice.service.item;

import org.example.orderservice.dto.item.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> findAllItems();
    void findDeleteByIdItem(Long id);
    ItemDto insertItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto);
}
