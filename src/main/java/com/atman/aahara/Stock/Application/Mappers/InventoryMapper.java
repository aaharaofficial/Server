package com.atman.aahara.Stock.Application.Mappers;

import com.atman.aahara.Assets.Adapter.ImageMapper;
import com.atman.aahara.Stock.Domain.Model.Inventory;
import com.atman.aahara.Stock.Infra.Persistance.InventoryEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring" ,uses = {ImageMapper.class})
public interface InventoryMapper {

    Inventory toDomain(InventoryEntity entity);

    InventoryEntity toEntity(Inventory domain);
}
