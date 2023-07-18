package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class EventResponse {
    private UUID orderNumber;
}
