package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.*;
import com.example.warehousemanagementlufthansa.repository.InventoryBodyRepository;
import com.example.warehousemanagementlufthansa.repository.ItemRepository;
import com.example.warehousemanagementlufthansa.repository.OrderRepository;
import com.example.warehousemanagementlufthansa.repository.TrucksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
    @Autowired
    TrucksRepository trucksRepository;

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
    public InventoryHeader findById(Long id) {
        return orderRepository.getById(id) ;
    }

    @Override
    public InventoryHeader update(Long id, InventoryHeader inventoryHeader) throws NotFoundException {
        log.info("Updated order{}" );
        InventoryHeader updated = orderRepository.getById(id);
        updated.setAppUser(inventoryHeader.getAppUser());
        updated.setStatus(inventoryHeader.getStatus());
        updated.setInventoryBodyList(inventoryHeader.getInventoryBodyList());
        updated.setCreatedAt(inventoryHeader.getCreatedAt());
        updated.setLastModified(inventoryHeader.getLastModified());

        return orderRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public InventoryHeader cancel(Long id) {
        InventoryHeader inventoryHeader = orderRepository.getById(id);
        if(OrderStatus.FULFILLED.equals(true) || OrderStatus.UNDER_DELIVERY.equals(true) || OrderStatus.CANCELLED.equals(true)){
            log.info("Cannot cancel this order");
        }
        inventoryHeader.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(inventoryHeader);
    }

    @Override
    public InventoryHeader submit(Long id) {
        InventoryHeader inventoryHeader = orderRepository.getById(id);
        if(OrderStatus.CREATED.equals(true) || OrderStatus.DECLINED.equals(true)){
            log.info("You submitted this order {}" , inventoryHeader);
            inventoryHeader.setStatus(OrderStatus.AWAITING_APPROVAL);
        }else{
            log.error("Something went wrong!");
        }
        return null;
    }

    @Override
    public List<InventoryHeader> findAll(Specification<InventoryHeader> spec) {
        return orderRepository.findAll(spec);
    }

    @Override
    public InventoryHeader scheduleDelivery(Date date , Trucks trucks) {

        return null;
    }

}
