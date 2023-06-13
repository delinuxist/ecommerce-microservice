package com.example.orderservice.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public record OrderRequestDto(
        List<OrderLineItemsDto> orderLineItems
) {
}
