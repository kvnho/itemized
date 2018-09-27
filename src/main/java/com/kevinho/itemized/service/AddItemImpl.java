package com.kevinho.itemized.service;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddItemImpl implements AddItemService {
    @Autowired
    private ItemRepository itemRepository;


    @Override
    public void saveItem(Item itemDAO) {
        Item item = new Item();
        item.setName(itemDAO.getName());
        item.setColor(itemDAO.getColor());
        item.setSize(itemDAO.getSize());
        item.setPrice(itemDAO.getPrice());
        itemRepository.save(item);
    }
}
