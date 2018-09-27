package com.kevinho.itemized.service;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveItemServiceImpl implements RemoveItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void removeItem(Item item) {
        itemRepository.delete(item);
    }
}
