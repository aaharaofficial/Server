package com.atman.aahara.Offer.InventoryOffer;

import com.atman.aahara.Inventory.InventoryMapper;
import com.atman.aahara.Offer.InventoryOffer.Dto.InventoryOfferRequestDto;
import com.atman.aahara.Offer.InventoryOffer.Dto.InventoryOfferResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InventoryMapper.class})
public interface InventoryOfferMapper {

    /* -------------------- Request → Entity -------------------- */
    @Named("requestToEntity")
    @Mapping(target = "inventories", ignore = true)
    InventoryOffer requestToEntity(InventoryOfferRequestDto dto);

    @IterableMapping(qualifiedByName = "requestToEntity")
    List<InventoryOffer> requestToEntity(List<InventoryOfferRequestDto> dtos);

    /* -------------------- Entity → Response -------------------- */
    @Named("entityToResponse")
    InventoryOfferResponseDto entityToResponse(InventoryOffer entity);

    @IterableMapping(qualifiedByName = "entityToResponse")
    List<InventoryOfferResponseDto> entityToResponse(List<InventoryOffer> entities);

    /* -------------------- Update Existing -------------------- */

    @Mapping(target = "inventories" , ignore = true)
    InventoryOffer mapExisting(InventoryOfferRequestDto dto,
                               @MappingTarget InventoryOffer entity);

}
