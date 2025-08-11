package org.example.orderservice.service.item.impl;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.entity.Item;
import org.example.orderservice.mapper.ItemMapper;
import org.example.orderservice.repository.ItemRepository;
import org.example.orderservice.service.item.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> findAllItems() {
        List<Item> all = itemRepository.findAll();
        return all
                .stream()
                .map(itemMapper::itemToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void findDeleteByIdItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ItemDto insertItem(ItemDto itemDto) {
        Item entityDto = itemMapper.toEntityDto(itemDto);
        Item save = itemRepository.save(entityDto);
        return itemMapper.itemToDto(save);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto) {
        Item entityDto = itemMapper.toEntityDto(itemDto);
        Item save = itemRepository.save(entityDto);
        return itemMapper.itemToDto(save);

    }
}
