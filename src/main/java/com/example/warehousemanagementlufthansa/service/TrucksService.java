package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Trucks;

import java.util.List;

public interface TrucksService {
    List<Trucks> getAllTrucks();
    Trucks save(Trucks trucks);
    Trucks update(Long id);
    void delete(Long id);
}
