package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.entity.OrderLineItems;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderLineItemsDtoMapper implements Function<OrderLineItemsDto,OrderLineItems> {

    @Override
    public OrderLineItems apply(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.price())
                .quantity(orderLineItemsDto.quantity())
                .skuCode(orderLineItemsDto.skuCode())
                .build();
    }
}
