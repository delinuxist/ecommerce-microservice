package com.example.orderservice.service.impl;

import com.example.orderservice.dto.EventResponse;
import com.example.orderservice.dto.InventoryResponseDto;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderLineItems;
import com.example.orderservice.mapper.OrderLineItemsDtoMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderLineItemsDtoMapper orderLineItemsDtoMapper;

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String,EventResponse> kafkaTemplate;

    @Override
    public String placeOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .oderNumber(UUID.randomUUID())
                .build();

        List<OrderLineItems> orderLineItems = orderRequestDto.orderLineItems()
                .stream()
                .map(orderLineItemsDtoMapper)
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode).toList();

       InventoryResponseDto[]  inventoryResponses =  webClientBuilder.build().get()
                        .uri("lb://inventory-service/inventory",uriBuilder -> uriBuilder
                                .queryParam("skuCode",skuCodes)
                                .build()
                        )
                                .retrieve()
                                        .bodyToMono(InventoryResponseDto[].class)
                                                .block();

        assert inventoryResponses != null;

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponseDto::isInStock);

        if (!allProductsInStock) {
            throw new RuntimeException("Product is  not in stock");
        }

       orderRepository.save(order);
        kafkaTemplate.send("notificationTopic", EventResponse.builder().orderNumber(order.getOderNumber()).build());
        return "Order place successfully";
    }
}
