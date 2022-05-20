package com.prr.warehouse.entities;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseInventory {
    @NonNull
    private Integer warehouse_id;
    @NonNull
    private Integer inventory_id;
    @NonNull
    private Integer inventory_count;

    public void decrementCount(int count) {
        this.inventory_count -= count;
    }

    public void incrementCount(int count) {
        this.inventory_count += count;
    }
}
