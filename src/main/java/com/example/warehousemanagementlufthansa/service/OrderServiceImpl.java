package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.InventoryBody;
import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.repository.InventoryBodyRepository;
import com.example.warehousemanagementlufthansa.repository.ItemRepository;
import com.example.warehousemanagementlufthansa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InventoryBodyRepository inventoryBodyRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<InventoryHeader> getAll() {
        log.info("FetchAll {}" );
        return orderRepository.findAll();
    }

    @Override
    public InventoryHeader save(InventoryHeader inventoryHeader ) {
        log.info("Saving an order {}", inventoryHeader );
        for (InventoryBody body : inventoryHeader.getInventoryBodyList()) {
            Item item = itemRepository.getById(body.getItemId());
            BigDecimal quantity;
            if(item.getQuantity().compareTo(body.getQuantity())< 0){
                log.info("there's not enough items in the warehouse");
                quantity = item.getQuantity();
            }else {
                quantity = item.getQuantity().subtract(body.getQuantity());
            }
            item.setQuantity(quantity);
            itemRepository.save(item);
        }

        return orderRepository.save(inventoryHeader);

    }

    @Override
    public List<InventoryBody> getAllItems() {
        log.info("FetchAll items{}" );

        return inventoryBodyRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
