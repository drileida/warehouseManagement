package com.example.warehousemanagementlufthansa.service;

import com.example.warehousemanagementlufthansa.model.Item;
import com.example.warehousemanagementlufthansa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Override
    public List<Item> getAllItems() {
        log.info("Fetching all items {}" );
        return itemRepository.findAll();
    }

    @Override
    public Item save(Item item) {
        log.info("Saving item {}"  , item.getItemName());
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = new Item();
        log.info("Deleting item {}"  , item.getItemName());
        itemRepository.deleteById(item.getId());
    }


}
