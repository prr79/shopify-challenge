package com.prr.warehouse.services;

import com.prr.warehouse.entities.Inventory;
import com.prr.warehouse.repositories.InventoryRepository;
import com.prr.warehouse.repositories.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WarehouseInventoryRepository warehouseInventoryRepository;

    public Integer updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer id, String deletion_comments) {
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            inventory.setHidden(true);
            inventory.setDeletionComments(deletion_comments);
            inventoryRepository.save(inventory);
        }
    }

    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id);
    }

    public void enableInventory(Integer id) {
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            inventory.setHidden(false);
            inventoryRepository.save(inventory);
        }
    }

    public Map<Integer, Integer> getInventoryLocations(Integer id) {
        return warehouseInventoryRepository.getWarehousesForInventory(id);
    }

    public List<Inventory> getAllEnabledInventory() {
        return inventoryRepository.getAllInventory();
    }
}
