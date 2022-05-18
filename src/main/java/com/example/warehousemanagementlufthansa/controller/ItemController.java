package com.example.warehousemanagementlufthansa.controller;

import com.example.warehousemanagementlufthansa.domain.User;
import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_MANAGER')")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getUsers() {
        return ResponseEntity.ok().body(itemService.getAllItems());}


    @PostMapping("/item/save")
    public ResponseEntity<Item> saveUser(@RequestBody Item item){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/item/save").toUriString());
        return ResponseEntity.created(uri).body(itemService.save(item));
    }

}
