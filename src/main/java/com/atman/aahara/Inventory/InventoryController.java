package com.atman.aahara.Inventory;

import com.atman.aahara.Inventory.Dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Inventory API")
public class InventoryController  {

    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a new inventory item")
    public ResponseEntity<InventoryResponse> createInventory(
            @ModelAttribute InventoryRequest request
    ) throws IOException {
        Inventory inventory = inventoryService.createInventory(request);
        return ResponseEntity.ok(inventoryMapper.EntityToResponse(inventory));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update an existing inventory item")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable UUID id,
            @ModelAttribute InventoryUpdateRequest request
    ) throws IOException {
        Inventory inventory = inventoryService.updateInventory(id, request);
        return ResponseEntity.ok(inventoryMapper.EntityToResponse(inventory));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an inventory item by ID")
    public ResponseEntity<Void> deleteInventory(@PathVariable UUID id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an inventory item by ID")
    public ResponseEntity<InventoryResponseWithOfferDto> getInventory(@PathVariable UUID id) {
        Inventory inventory = inventoryService.getInventory(id);
        return ResponseEntity.ok(inventoryMapper.EntityToResponseWithOffer(inventory));
    }

    @GetMapping
    @Operation(summary = "Get All inventory item ")
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        List<Inventory> inventory = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventoryMapper.EntityToResponse(inventory));
    }
}
