package com.prr.warehouse.entities.keys;

import com.prr.warehouse.entities.WarehouseInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInventoryKey {
    private Integer warehouse_id;
    private Integer inventory_id;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WarehouseInventoryKey)) {
            return false;
        }
        WarehouseInventoryKey warehouseInventoryKey = (WarehouseInventoryKey) obj;
        return inventory_id.equals(warehouseInventoryKey.inventory_id) && warehouse_id.equals(warehouseInventoryKey.warehouse_id);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(warehouse_id) * Integer.hashCode(inventory_id);
    }

//    @Override
//    public String toString() {
//        return String.format("{%s, %s}", warehouse_id, inventory_id);
//    }
}
