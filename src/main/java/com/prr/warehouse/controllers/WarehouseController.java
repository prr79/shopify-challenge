package com.prr.warehouse.controllers;

import com.prr.warehouse.entities.Warehouse;
import com.prr.warehouse.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable("id") Integer id) {
        return warehouseService.getWarehouseDetails(id);
    }

//    @PostMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpStatus createWarehouse(@RequestBody Warehouse warehouse) {
        try {
            warehouseService.updateWarehouse(warehouse);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.CREATED;
    }

    @PostMapping("/enable/{id}")
    public HttpStatus enableWarehouse(@PathVariable Integer id) {
        try {
            warehouseService.enableWarehouse(id);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteWarehouse(@PathVariable Integer id, @RequestParam(required = false, value = "deleted") String deletion_comments) {
        try {
            warehouseService.deleteWareHouse(id, deletion_comments);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus updateWarehouse(@RequestBody Warehouse warehouse) {
        try {
            warehouseService.updateWarehouse(warehouse);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @PostMapping("/addInventoryToWarehouse")
    public HttpStatus addInventoryToWarehouse(@RequestParam Integer warehouse_id, @RequestParam Integer inventory_id, int inventory_count) {
        int status = warehouseService.addInventoryToWarehouse(warehouse_id, inventory_id, inventory_count);

        return status>0? HttpStatus.ACCEPTED: HttpStatus.NOT_IMPLEMENTED;
    }

    @GetMapping("{id}/inventory")
    public Map<Integer, Integer> getInventoryAtWarehouse(@PathVariable Integer id) {
        return warehouseService.getInventoryAtWarehouse(id);
    }
}
