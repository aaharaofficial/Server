package com.atman.aahara.Inventory.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryUpdateRequest {
    private String name;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    private String description;
    private MultipartFile image;
    private boolean isAssetChanged;
}
