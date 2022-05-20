package com.prr.warehouse.services;

import com.prr.warehouse.entities.Inventory;
import com.prr.warehouse.entities.Warehouse;
import com.prr.warehouse.entities.WarehouseInventory;
import com.prr.warehouse.entities.keys.WarehouseInventoryKey;
import com.prr.warehouse.repositories.WarehouseInventoryRepository;
import com.prr.warehouse.repositories.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarehouseService {
    private static Logger LOGGER = LoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    private InventoryService inventoryService;

    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void deleteWareHouse(Integer id, String deletion_comments) {
        warehouseInventoryRepository.deleteWarehouseInventoriesByWarehouseId(id);
        Warehouse warehouse = warehouseRepository.findById(id);
        if (warehouse != null) {
            warehouse.setHidden(true);
            warehouse.setDeletionComments(deletion_comments);
            warehouseRepository.save(warehouse);
        };
    }

    public Warehouse getWarehouseDetails(Integer id) {
        return warehouseRepository.findById(id);
    }

    public Map<Integer, Integer> getInventoryAtWarehouse(Integer id) {
        Map<Integer, Integer> inventoryList = warehouseInventoryRepository.getInventoryAtWarehouse(id);

        return inventoryList;
    }

    public void enableWarehouse(Integer id) {
        Warehouse warehouse = warehouseRepository.findById(id);
        if (warehouse != null) {
            warehouse.setHidden(false);
            warehouseRepository.save(warehouse);
        };
    }

    public Integer pickInventoryFromWarehouse(Integer id, Integer inventory_id, int count) {
        Integer current_inventory_count_at_warehouse = warehouseInventoryRepository.pickInventoryFromWarehouse(id, inventory_id, count);

        return current_inventory_count_at_warehouse;
    }

    public Integer addInventoryToWarehouse(Integer id, Integer inventory_id, int count) {
        Inventory inventory = inventoryService.getInventoryById(inventory_id);
        if (Optional.ofNullable(inventory).map(Inventory::isHidden).orElse(true)) {
            LOGGER.error("Inventory {} doesn't exist or isn't active", inventory_id);
            return -1;
        }
        Warehouse warehouse = getWarehouseDetails(id);
        if (Optional.ofNullable(warehouse).map(Warehouse::isHidden).orElse(true)) {
            LOGGER.error("Warehouse {} doesn't exist or isn't active", id);
            return -2;
        }

        warehouseInventoryRepository.addInventoryToWarehouse(id, inventory_id, count);

        return warehouseInventoryRepository.getInventoryCountAtWarehouse(id, inventory_id);
    }
}
