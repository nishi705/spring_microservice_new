package com.programmingtechie.inventory_service.controller;

import com.programmingtechie.inventory_service.dao.InventoryResponse;
import com.programmingtechie.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getInventory(@RequestParam List<String> skuCode) throws InterruptedException {
        return inventoryService.isInStock(skuCode);
    }
}
