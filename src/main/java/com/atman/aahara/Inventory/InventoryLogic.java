package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Assets.Image.ImageService;
import com.atman.aahara.Exception.ResourceNotFoundException;
import com.atman.aahara.Inventory.Dto.InventoryRequest;
import com.atman.aahara.Inventory.Dto.InventoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryLogic implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventorySpecification inventorySpecification;
    private final ImageService imageService;

    /**
     * Create a new inventory item with image.
     */
    @Transactional
    @Override
    public Inventory createInventory(InventoryRequest request) throws IOException {
        Image image = imageService.saveImage(request.getImage(), "");
        log.info("imagr" + image.getRawImage());
        log.info(" y nothing"+ request.getImage().getName(), request.getImage().getContentType());
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
    @Override
    public Inventory updateInventory(UUID id, InventoryUpdateRequest request) throws IOException {
        Inventory inventory = getInventory(id);

        if (request.isAssetChanged()) {
            Image updatedImage = imageService.replaceimage(inventory.getImage(), request.getImage(), "");
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
    @Override
    public void deleteInventory(UUID id) {
        Inventory inventory = getInventory(id);
        imageService.deleteImage(inventory.getImage());
        inventoryRepository.delete(inventory);
    }

    /**
     * Fetch inventory by ID, throws exception if not found.
     */
    @Override
    public Inventory getInventory(UUID id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found: " + id));
    }


    @Override
    public List<Inventory>  getInventoryForIds(List<UUID> ids){
        return inventoryRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
}
