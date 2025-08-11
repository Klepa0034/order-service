package org.example.orderservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.item.ItemDto;
import org.example.orderservice.service.item.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemRestController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getItems() {
        return itemService.findAllItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findDeleteByIdItem(@PathVariable Long id) {

        itemService.findDeleteByIdItem(id);
    }

    @PostMapping
    public ResponseEntity<ItemDto> insertItem(@RequestBody ItemDto itemDto) {
        ItemDto item = itemService.insertItem(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    @PutMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemDto itemDto) {
        return itemService.updateItem(itemDto);
    }

}
