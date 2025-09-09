package com.atman.aahara.Assets.Adapter;

import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Assets.Infra.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageEntity toEntity(Image image);

    Image toDomain(ImageEntity entity);
}
