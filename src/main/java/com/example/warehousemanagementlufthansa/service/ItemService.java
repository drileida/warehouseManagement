package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();
    Item save(Item item);
    void deleteItem(Long id);
//    Item update(Long id , Item item);

}
