package com.atman.aahara.Inventory.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryResponse {
    private UUID id;
    private String name;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    private String description;
    private String image;
}
