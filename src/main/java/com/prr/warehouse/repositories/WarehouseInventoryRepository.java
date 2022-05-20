package com.prr.warehouse.repositories;

import com.prr.warehouse.entities.Inventory;
import com.prr.warehouse.entities.WarehouseInventory;
import com.prr.warehouse.entities.keys.WarehouseInventoryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.web3j.tuples.generated.Tuple2;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WarehouseInventoryRepository {
    private static Logger LOGGER = LoggerFactory.getLogger(WarehouseInventoryRepository.class);

    private Map<WarehouseInventoryKey, Integer> repository = new HashMap<>();

    public Map<Integer, Integer> getInventoryAtWarehouse(int warehouse_id) {
        Map<Integer, Integer> inventory_map = repository
                .entrySet()
                .stream()
                .filter(row ->
                        row.getKey().getWarehouse_id().equals(warehouse_id)
                                && row.getValue()>0)
                .collect(Collectors.toMap(
                        row -> row.getKey().getInventory_id(),
                        Map.Entry::getValue));

        return inventory_map;
    }

    public Integer getInventoryCountAtWarehouse(Integer warehouse_id, Integer inventory_id) {
        WarehouseInventoryKey key = WarehouseInventoryKey
                .builder()
                .warehouse_id(warehouse_id)
                .inventory_id(inventory_id)
                .build();
        return repository.get(key);
    }

    public void deleteWarehouseInventoriesByWarehouseId(Integer warehouse_id) {
        List<WarehouseInventoryKey> inventory_at_warehouse = repository
                .entrySet()
                .stream()
                .filter(row ->
                        row.getKey().getWarehouse_id().equals(warehouse_id)).map(Map.Entry::getKey).collect(Collectors.toList());
        inventory_at_warehouse.forEach(key -> repository.remove(key));
    }

    public Integer pickInventoryFromWarehouse(Integer warehouse_id, Integer inventory_id, Integer count) {
        WarehouseInventoryKey key = WarehouseInventoryKey
                .builder()
                .warehouse_id(warehouse_id)
                .inventory_id(inventory_id)
                .build();
        Integer current_inventory_count = repository.get(key);
        if ((current_inventory_count != null) && (current_inventory_count >= count)) {
            current_inventory_count -= count;
            repository.put(key, current_inventory_count);
            return current_inventory_count;
        }
        LOGGER.info("Warehouse [{}]: Inventory {} doesn't exist or is insufficient", warehouse_id, inventory_id);
        return null;
    }

    public Integer addInventoryToWarehouse(Integer warehouse_id, Integer inventory_id, Integer count) {
        WarehouseInventoryKey key = WarehouseInventoryKey
                .builder()
                .warehouse_id(warehouse_id)
                .inventory_id(inventory_id)
                .build();
        Integer current_inventory_count = repository.getOrDefault(key, 0);

        return repository.put(key, current_inventory_count + count);
    }

    public Map<Integer, Integer> getWarehousesForInventory(int inventory_id) {
        Map<Integer, Integer> inventory_map = repository
                .entrySet()
                .stream()
                .filter(row ->
                        row.getKey().getInventory_id().equals(inventory_id)
                                && row.getValue()>0)
                .collect(Collectors.toMap(
                        row -> row.getKey().getWarehouse_id(),
                        Map.Entry::getValue));

        return inventory_map;
    }
}
