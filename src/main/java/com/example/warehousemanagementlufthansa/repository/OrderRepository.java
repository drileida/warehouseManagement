package com.example.warehousemanagementlufthansa.repository;

import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<InventoryHeader, Long> ,JpaSpecificationExecutor<InventoryHeader> {
}
