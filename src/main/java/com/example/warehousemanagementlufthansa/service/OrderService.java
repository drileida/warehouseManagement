package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.InventoryBody;
import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.Trucks;
import org.springframework.data.jpa.domain.Specification;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<InventoryHeader> getAll();
    InventoryHeader save(InventoryHeader inventoryHeader);
    List<InventoryBody> getAllItems();
    InventoryHeader findById(Long id);
    InventoryHeader update(Long id, InventoryHeader inventoryHeader) throws NotFoundException;
    void delete(Long id);
    InventoryHeader cancel(Long id);
    InventoryHeader submit(Long id);
    List<InventoryHeader> findAll(Specification<InventoryHeader> spec);
    InventoryHeader scheduleDelivery(Date date, Trucks trucks);
}
