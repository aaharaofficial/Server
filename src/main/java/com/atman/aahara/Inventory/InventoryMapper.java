package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Inventory.Dto.InventoryRequest;
import com.atman.aahara.Inventory.Dto.InventoryResponse;
import com.atman.aahara.Inventory.Dto.InventoryResponseWithOfferDto;
import com.atman.aahara.Inventory.Dto.InventoryUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {


    @Mapping(target = "image",ignore = true)
    Inventory RequestToEntity(InventoryRequest inventoryRequest);

    @Mapping(target = "image",ignore = true)
    Inventory RequestToEntity(InventoryUpdateRequest inventoryUpdateRequest);

    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
    InventoryResponse EntityToResponse(Inventory inventory);

    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
    @Mapping(target = "offerId" , source = "offer.id")
    @Mapping(target = "offerName" , source = "offer.name")
    @Mapping(target = "offerDiscountValue" , source = "offer.discountValue")
    @Mapping(target = "offerEndDate" , source = "offer.endDate")
    @Mapping(target = "offerStock", source = "offer.remainingStock")
    InventoryResponseWithOfferDto EntityToResponseWithOffer(Inventory inventory);

    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
    List<InventoryResponse> EntityToResponse(List<Inventory> inventory);

    @Named("mapImage")
    default String mapImage(Image image) {
        if (image == null) {
            return null;
        }
        return image.isEncoded() ? image.getEncodedImage() : null;
    }
}
