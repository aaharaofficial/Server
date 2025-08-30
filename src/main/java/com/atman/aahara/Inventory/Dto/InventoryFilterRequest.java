package com.atman.aahara.Inventory.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryFilterRequest {
    private String name;
    private String description;
    private Double minPrice;
    private Double maxPrice;
}
