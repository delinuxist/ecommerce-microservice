package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.dto.InventoryResponseDto;
import com.example.inventoryservice.mapper.InventoryResponseDtoMapper;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImp implements InventoryService {

    private final  InventoryRepository inventoryRepository;

    private final InventoryResponseDtoMapper inventoryResponseDtoMapper;

    @Override
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventoryResponseDtoMapper).toList();
    }
}
