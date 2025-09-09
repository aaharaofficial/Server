package com.atman.aahara.Assets.Adapter;

import com.atman.aahara.Assets.Domain.Video;
import com.atman.aahara.Assets.Infra.VideoEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoEntity toEntity(Video video);

    Video toDomain(VideoEntity entity);
}
