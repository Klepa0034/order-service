package org.example.orderservice.mapper;

import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto itemToDto(Item item);

    Item toEntityDto(ItemDto itemDto);
}
