package com.example.inventoryservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponseDto(
    String skuCode,
    boolean isInStock

) {
}
