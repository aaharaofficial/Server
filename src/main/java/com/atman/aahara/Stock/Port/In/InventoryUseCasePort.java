package com.atman.aahara.Stock.Port.In;


import com.atman.aahara.Stock.Domain.Model.Inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryUseCasePort {

    Inventory createInventory(Inventory inventory);

    Inventory updateInventoryPrice(UUID id, BigDecimal newPrice);

    Optional<Inventory> getInventoryById(UUID id);

    List<Inventory> getAllInventories();

    void deleteInventory(UUID id);
}
