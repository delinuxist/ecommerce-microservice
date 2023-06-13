package com.example.orderservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderLineItemsDto(
         String skuCode,
         BigDecimal price,
         Integer quantity

) {
}
