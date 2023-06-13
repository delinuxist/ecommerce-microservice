package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    public ResponseEntity<List<InventoryResponseDto>> isInStock(@RequestParam List<String> skuCode) {
        return ResponseEntity.ok().body(inventoryService.isInStock(skuCode));
    }
}
