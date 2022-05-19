package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Trucks;
import com.example.warehousemanagementlufthansa.repository.TrucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrucksServiceImpl implements TrucksService {
    @Autowired
    TrucksRepository trucksRepository;

    @Override
    public List<Trucks> getAllTrucks() {
        return trucksRepository.findAll();
    }

    @Override
    public Trucks save(Trucks trucks) {
        return trucksRepository.save(trucks);
    }

    @Override
    public Trucks update(Long id) {
        Trucks trucks = trucksRepository.getById(id);
        return trucksRepository.save(trucks);
    }

    @Override
    public void delete(Long id) {
        trucksRepository.deleteById(id);
    }
}
