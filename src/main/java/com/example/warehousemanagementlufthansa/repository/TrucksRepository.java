package com.example.warehousemanagementlufthansa.repository;

import com.example.warehousemanagementlufthansa.model.Trucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucksRepository extends JpaRepository<Trucks, Long> {
}
