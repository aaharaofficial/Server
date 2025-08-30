package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Exception.ResourceNotFoundException;
import com.atman.aahara.Inventory.Dto.InventoryFilterRequest;
import com.atman.aahara.Inventory.Dto.InventoryRequest;
import com.atman.aahara.Inventory.Dto.InventoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryLogic {

    private final InventoryRepository inventoryRepository;
    private final InventorySpecification inventorySpecification;
    private final InventoryImageService inventoryImageService;

    /**
     * Create a new inventory item with image.
     */
    @Transactional
    public Inventory createInventory(InventoryRequest request) throws IOException {
        Image image = inventoryImageService.saveImage(request.getImage());

        Inventory inventory = Inventory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .rawPrice(request.getRawPrice())
                .image(image)
                .build();

        return inventoryRepository.save(inventory);
    }

    /**
     * Update existing inventory item.
     */
    @Transactional
    public Inventory updateInventory(UUID id, InventoryUpdateRequest request) throws IOException {
        Inventory inventory = getInventory(id);

        if (request.isAssetChanged()) {
            Image updatedImage = inventoryImageService.replaceImage(inventory.getImage(), request.getImage());
            inventory.setImage(updatedImage);
        }

        inventory.setName(request.getName());
        inventory.setDescription(request.getDescription());
        inventory.setRawPrice(request.getRawPrice());

        return inventoryRepository.save(inventory);
    }

    /**
     * Delete inventory item along with its image.
     */
    @Transactional
    public void deleteInventory(UUID id) {
        Inventory inventory = getInventory(id);
        inventoryImageService.deleteImage(inventory.getImage());
        inventoryRepository.delete(inventory);
    }

    /**
     * Fetch inventory by ID, throws exception if not found.
     */
    public Inventory getInventory(UUID id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found: " + id));
    }

    /**
     * Filter inventory by multiple filter request.
     */
    public List<Inventory> filterInventories(InventoryFilterRequest filter) {
        Specification<Inventory> spec = inventorySpecification.hasName(filter.getName())
                .and(inventorySpecification.descriptionContains(filter.getDescription()))
                .and(inventorySpecification.priceBetween(filter.getMinPrice(), filter.getMaxPrice()));

        return inventoryRepository.findAll(spec);
    }
}
