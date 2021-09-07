package com.lesson5.task1.service;

import com.lesson5.task1.DAO.ItemDAO;
import com.lesson5.task1.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemDAO itemDAO;

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public Item update(Item item) {
        return itemDAO.update(item);
    }

    public void delete(Long id) {
        itemDAO.delete(id);
    }

    public Item findById(Long id) {
        return itemDAO.findById(id);
    }
}
