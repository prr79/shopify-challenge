package com.prr.warehouse.repositories;

import com.prr.warehouse.entities.Warehouse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class WarehouseRepository {
    private Map<Integer, Warehouse> repository = new HashMap<>();
    private Integer index = 0;

    private Integer getNextIndex() {
        index++;
        return index;
    }

    public Integer save(Warehouse warehouse) {
        Integer id = Optional.ofNullable(warehouse.getId()).orElse(getNextIndex());
        warehouse.setId(id);
        repository.put(id, warehouse);
        return id;
    }


    public Warehouse findById(Integer id) {
        return repository.get(id);
    }
}
