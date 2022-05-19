package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Delivery;
import com.example.warehousemanagementlufthansa.model.Trucks;
import com.example.warehousemanagementlufthansa.repository.DeliveryRepository;
import com.example.warehousemanagementlufthansa.repository.OrderRepository;
import com.example.warehousemanagementlufthansa.repository.TrucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public Delivery schedule(Date date , List<Trucks> trucks) {
        Delivery delivery = new Delivery();
        delivery.setDate(date);
        delivery.setTrucksList(trucks);

        delivery.getInventoryHeaderList();
        return deliveryRepository.save(delivery);
    }
}
