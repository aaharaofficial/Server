package com.atman.aahara.Stock.Adapter.Out;

import com.atman.aahara.Stock.Application.Mappers.InventoryMapper;
import com.atman.aahara.Stock.Domain.Model.Inventory;
import com.atman.aahara.Stock.Infra.Persistance.InventoryEntity;
import com.atman.aahara.Stock.Infra.Repository.InventoryJpaRepository;
import com.atman.aahara.Stock.Port.Out.InventoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryRepositoryPort {

    private final InventoryJpaRepository inventoryJpaRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity entity = inventoryMapper.toEntity(inventory);
        InventoryEntity saved = inventoryJpaRepository.save(entity);
        return inventoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Inventory> findById(UUID id) {
        return inventoryJpaRepository.findById(id)
                .map(inventoryMapper::toDomain);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryJpaRepository.findAll().stream()
                .map(inventoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        inventoryJpaRepository.deleteById(id);
    }

    @Override
    public void delete(Inventory inventory) {
        InventoryEntity entity = inventoryMapper.toEntity(inventory);
        inventoryJpaRepository.delete(entity);
    }
}
