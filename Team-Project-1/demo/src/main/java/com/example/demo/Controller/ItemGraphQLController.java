package com.example.demo.Controller;

import com.example.demo.Model.Item;
import com.example.demo.Service.ItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ItemGraphQLController {
    private final ItemService itemService;

    public ItemGraphQLController(ItemService itemService) {
        this.itemService = itemService;
    }

    @QueryMapping
    public List<Item> items() {
        return itemService.getAllItems();
    }

    @QueryMapping
    public Item item(@Argument Long id) {
        return itemService.getItemById(id).orElse(null);
    }

    @MutationMapping
    public Item createItem(@Argument String name, @Argument String description) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        return itemService.createItem(item);
    }
}