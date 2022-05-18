package com.example.warehousemanagementlufthansa.controller;

import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InventoryController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<InventoryHeader>> getOrders() {
        return ResponseEntity.ok().body(orderService.getAll());}

    @PostMapping("/order/save")
    public ResponseEntity<InventoryHeader> saveOrder(@RequestBody InventoryHeader inventoryHeader){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/order/save").toUriString());
        return ResponseEntity.created(uri).body(orderService.save(inventoryHeader));
    }
}
