package com.prr.warehouse.repositories;

import com.prr.warehouse.entities.Inventory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InventoryRepository {
    private Map<Integer, Inventory> repository = new HashMap<>();
    private Integer index = 0;

    private Integer getNextIndex() {
        index++;
        return index;
    }
    
    public Integer save(Inventory inventory) {
        Integer id = Optional.ofNullable(inventory.getId()).orElse(getNextIndex());
        inventory.setId(id);
        repository.put(id, inventory);
        return id;
    }


    public Inventory findById(Integer id) {
        return repository.get(id);
    }

    public List<Inventory> getAllInventory() {
        return repository.values().stream().filter(inventory -> !inventory.isHidden()).collect(Collectors.toList());
    }
}
