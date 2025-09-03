package com.atman.aahara.Inventory;

import com.atman.aahara.Inventory.Dto.InventoryFilterRequest;
import com.atman.aahara.Inventory.Dto.InventoryRequest;
import com.atman.aahara.Inventory.Dto.InventoryUpdateRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface InventoryService {

    @Transactional
    Inventory createInventory(InventoryRequest inventoryRequest) throws IOException;

    @Transactional
    Inventory updateInventory(UUID id, InventoryUpdateRequest request) throws IOException;

    @Transactional
    void deleteInventory(UUID id);

    Inventory getInventory(UUID id);

    List<Inventory>  getInventoryForIds(List<UUID> ids);

    List<Inventory> getAllInventory();
}
