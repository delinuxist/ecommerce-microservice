package com.example.orderservice.dto;

import lombok.Builder;

@Builder
public record InventoryResponseDto(
    String skuCode,
    boolean isInStock

) {
}
