package com.atman.aahara.Assets.Adapter;


import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Assets.Infra.ImageEntity;
import com.atman.aahara.Assets.Infra.ImageJpaRepository;
import com.atman.aahara.Assets.Port.ImageStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageStoreAdapter implements ImageStorePort {

    private final ImageJpaRepository jpaRepository;
    private final ImageMapper imageMapper;

    @Override
    public Image save(Image image) {
        ImageEntity savedImage = jpaRepository.save(imageMapper.toEntity(image));
        return imageMapper.toDomain(savedImage);
    }

    @Override
    public Optional<Image> findById(UUID id) {
        return jpaRepository.findById(id).map(imageMapper::toDomain);
    }

    @Override
    public void delete(Image image) {
        jpaRepository.delete(imageMapper.toEntity(image));
    }
}
