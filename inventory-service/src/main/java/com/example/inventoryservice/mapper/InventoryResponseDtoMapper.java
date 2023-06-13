package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InventoryResponseDtoMapper implements Function<Inventory, InventoryResponseDto> {
    @Override
    public InventoryResponseDto apply(Inventory inventory) {
        return InventoryResponseDto.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
