package com.lesson1.hw1.service;

import com.lesson1.hw1.DAO.ItemDAO;
import com.lesson1.hw1.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemService {
    @Autowired
    private ItemDAO itemDAO;
    public Item save(Item item) {
        return itemDAO.save(item);
    }

    public Item update(Item item) {
        return itemDAO.update(item);
    }

    public void delete(Item item) {
        itemDAO.delete(item);
    }

    public List<Item> findById(long id) {
        return itemDAO.findById(id);
    }
}
