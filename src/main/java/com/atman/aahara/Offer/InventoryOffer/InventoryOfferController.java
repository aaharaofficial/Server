package com.atman.aahara.Offer.InventoryOffer;

import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Inventory.InventoryService;
import com.atman.aahara.Offer.InventoryOffer.Dto.InventoryOfferRequestDto;
import com.atman.aahara.Offer.InventoryOffer.Dto.InventoryOfferResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offer/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryOfferController {

    private final InventoryOfferMapper inventoryOfferMapper;
    private final InventoryOfferService inventoryOfferService;
    private final InventoryService inventoryService;

    @PostMapping
    @Operation(summary = "Create a new inventory offer")
    public ResponseEntity<InventoryOfferResponseDto> createInventoryOffer(
            @Valid @RequestBody InventoryOfferRequestDto inventoryOfferRequestDto) {
        List<Inventory> inventoryForIds = inventoryService.getInventoryForIds(inventoryOfferRequestDto.getInventories());
        InventoryOffer entity = inventoryOfferMapper.requestToEntity(inventoryOfferRequestDto);
        entity.setInventories(inventoryForIds);
        InventoryOffer saved = inventoryOfferService.saveRecipe(entity);
        return ResponseEntity.ok(inventoryOfferMapper.entityToResponse(saved));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing inventory offer")
    public ResponseEntity<InventoryOfferResponseDto> updateInventoryOffer(
            @PathVariable UUID id,
            @Valid @RequestBody InventoryOfferRequestDto inventoryOfferRequestDto) {
        InventoryOffer entity = inventoryOfferService.getOffer(id);
        InventoryOffer updatedEntity = inventoryOfferMapper.mapExisting(inventoryOfferRequestDto, entity);
        InventoryOffer saved = inventoryOfferService.saveRecipe(updatedEntity);
        return ResponseEntity.ok(inventoryOfferMapper.entityToResponse(saved));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an inventory offer by ID")
    public ResponseEntity<InventoryOfferResponseDto> getOfferById(@PathVariable UUID id) {
        InventoryOffer entity = inventoryOfferService.getOffer(id);
        return ResponseEntity.ok(inventoryOfferMapper.entityToResponse(entity));
    }

    @GetMapping
    @Operation(summary = "Get all inventory offers")
    public ResponseEntity<List<InventoryOfferResponseDto>> getAllOffer() {
        List<InventoryOffer> entities = inventoryOfferService.getAllOffer();
        return ResponseEntity.ok(inventoryOfferMapper.entityToResponse(entities));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an inventory offer by ID")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID id) {
        inventoryOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
