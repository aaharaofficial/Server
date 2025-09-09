package com.atman.aahara.Stock.Port.Out;

import com.atman.aahara.Stock.Domain.Model.Inventory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);
    Optional<Inventory> findById(UUID id);
    List<Inventory> findAll();
    void deleteById(UUID id);
    void delete(Inventory inventory);
}
