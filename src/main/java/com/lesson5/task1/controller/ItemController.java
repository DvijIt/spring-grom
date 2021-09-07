package com.lesson5.task1.controller;

import com.lesson5.task1.entity.Item;
import com.lesson5.task1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/item/save", produces = "text/plain")
    public @ResponseBody
    Item save(Item item) {
        return itemService.save(item);
    }

    @RequestMapping(method = RequestMethod.PUT,  value = "/item/update", produces = "text/plain")
    public @ResponseBody
    Item update(Item item) {
        return itemService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE,  value = "/item/delete", produces = "text/plain")
    public @ResponseBody
    void delete(@RequestParam("id") Long id) {
        itemService.delete(id);
    }

}
