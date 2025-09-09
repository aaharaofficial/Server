package com.atman.aahara.Planner.Items;

import com.atman.aahara.Dummy.Image.Image;
import com.atman.aahara.Planner.Items.Dto.PlannerItemRequestDto;
import com.atman.aahara.Planner.Items.Dto.PlannerItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlannerItemMapper {

    @Mapping(target = "name",source = "inventory.name")
    @Mapping(target = "pricePerQuantity",source = "inventory.pricePerQuantity")
    @Mapping(target = "unit",source = "inventory.unit")
    @Mapping(source = "image", target = "image", qualifiedByName = "mapImage")
    @Mapping(target = "quantity", source = "plannerItemRequestDto.quantity")
    @Mapping(target = "deliverySession", source = "plannerItemRequestDto.deliverySession")
    PlannerItem combineRequestAsEntity(Inventory inventory, PlannerItemRequestDto plannerItemRequestDto);

    PlannerItemResponseDto entityToResponse(PlannerItem plannerItem);


    @Named("mapImage")
    default String mapImage(Image image) {
        if (image == null) {
            return null;
        }
        return image.isEncoded() ? image.getEncodedImage() : null;
    }
}
