package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.InventoryBody;
import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.Item;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    List<InventoryHeader> getAll();
    InventoryHeader save(InventoryHeader inventoryHeader);
    List<InventoryBody> getAllItems();
    void delete(Long id);
}
