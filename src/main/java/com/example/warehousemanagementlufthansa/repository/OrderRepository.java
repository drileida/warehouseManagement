package com.example.warehousemanagementlufthansa.repository;

import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<InventoryHeader, Long> {
}
