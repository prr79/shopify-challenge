package com.prr.warehouse.controllers;

import com.prr.warehouse.entities.Inventory;
import com.prr.warehouse.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllEnabledInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable("id") Integer id) {
        return inventoryService.getInventoryById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public HttpStatus createInventory(@RequestBody Inventory inventory) {
        try {
            inventoryService.updateInventory(inventory);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.CREATED;
    }

    @RequestMapping(value = "/enable/{id}", method = RequestMethod.POST)
    public HttpStatus enableInventory(@PathVariable Integer id) {
        try {
            inventoryService.enableInventory(id);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteInventory(@PathVariable Integer id, @RequestParam(required = false, value = "deleted") String deletion_comments) {
        try {
            inventoryService.deleteInventory(id, deletion_comments);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus updateInventory(@RequestBody Inventory inventory) {
        try {
            inventoryService.updateInventory(inventory);
        } catch (Exception e) {
            return HttpStatus.NOT_IMPLEMENTED;
        }

        return HttpStatus.OK;
    }

    @GetMapping("/locations/{id}")
    public Map<Integer, Integer> getInventoryLocations(@PathVariable Integer id) {
        return inventoryService.getInventoryLocations(id);
    }
}
