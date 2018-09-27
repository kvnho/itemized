package com.kevinho.itemized.service;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowItemsServiceImpl implements ShowItemsService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }
}
