package com.atman.aahara.Stock.Application.Service;

import com.atman.aahara.Assets.Port.ImageUseCase;
import com.atman.aahara.Stock.Domain.Model.Inventory;
import com.atman.aahara.Stock.Port.Out.InventoryRepositoryPort;
import com.atman.aahara.Stock.Port.In.InventoryUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class InventoryApplicationService implements InventoryUseCasePort {

    private final InventoryRepositoryPort inventoryRepositoryPort;
    private final ImageUseCase imageUseCase;

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepositoryPort.save(inventory);
    }

    @Override
    public Inventory updateInventoryPrice(UUID id, BigDecimal newPrice) {
        Inventory inventory = inventoryRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
        return inventoryRepositoryPort.save(inventory);
    }

    @Override
    public Optional<Inventory> getInventoryById(UUID id) {
        return inventoryRepositoryPort.findById(id);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepositoryPort.findAll();
    }

    @Override
    public void deleteInventory(UUID id) {
        inventoryRepositoryPort.findById(id).ifPresent(inventoryRepositoryPort::delete);
    }
}
