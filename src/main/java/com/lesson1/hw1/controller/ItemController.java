package com.lesson1.hw1.controller;

import com.lesson1.hw1.entity.Item;
import com.lesson1.hw1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET,  value = "/item", produces = {"application/json", "text/xml", "text/plain"})
    public @ResponseBody
    String getItem() {
        return itemService.save(new Item(10L, "JACK")).toString();
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/item", produces = "text/plain")
    public @ResponseBody
    Item save(Item item) {
        return itemService.save(item);
    }

    @RequestMapping(method = RequestMethod.PUT,  value = "/item", produces = "text/plain")
    public @ResponseBody
    Item update(Item item) {
        return itemService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE,  value = "/item", produces = "text/plain")
    public @ResponseBody
    void delete(Item item) {
        itemService.delete(item);
    }

//    @RequestMapping(method = RequestMethod.GET,  value = "/item", produces = "text/plain")
//    public @ResponseBody
//    List<Item> findById(long id) {
//        return itemService.findById(id);
//    }
}
