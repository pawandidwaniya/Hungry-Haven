package com.cdac.hungryhaven.controllers;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.repositoryservices.ItemRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemRepositoryService itemRepositoryService;

    @Autowired
    public ItemController( ItemRepositoryService itemRepositoryService) {
        this.itemRepositoryService = itemRepositoryService;

    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody Item newItem) {
        String addedItem = itemRepositoryService.addItem(newItem);
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
        Item item = itemRepositoryService.findByItemId(itemId);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
