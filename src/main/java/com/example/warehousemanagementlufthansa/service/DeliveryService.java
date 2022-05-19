package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Delivery;
import com.example.warehousemanagementlufthansa.model.Trucks;

import java.util.Date;
import java.util.List;

public interface DeliveryService {
    Delivery schedule(Date date , List<Trucks> trucks);
}
