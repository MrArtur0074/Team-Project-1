package com.example.demo.Service;

import com.example.demo.Model.Item;
import com.example.demo.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return repository.findById(id);
    }

    public Item createItem(Item item) {
        return repository.save(item);
    }

    public Item updateItem(Long id, Item newItem) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setName(newItem.getName());
            existingItem.setDescription(newItem.getDescription());
            existingItem.setPrice(newItem.getPrice());
            return repository.save(existingItem);
        } else {
            throw new RuntimeException("Item not found");
        }
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}