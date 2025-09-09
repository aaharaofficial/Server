package com.atman.aahara.Assets.Adapter;

import com.atman.aahara.Assets.Domain.Video;
import com.atman.aahara.Assets.Infra.VideoJpaRepository;
import com.atman.aahara.Assets.Port.VideoStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VideoStoreAdapter implements VideoStorePort {

    private final VideoJpaRepository repository;
    private final VideoMapper videoMapper;

    @Override
    public Video save(Video video) {
        return videoMapper.toDomain(repository.save(videoMapper.toEntity(video)));
    }

    @Override
    public Optional<Video> findById(UUID id) {
        return repository.findById(id).map(videoMapper::toDomain);
    }

    @Override
    public void delete(Video video) {
        repository.delete(videoMapper.toEntity(video));
    }

    @Override
    public Video update(Video video) {
        return videoMapper.toDomain(repository.save(videoMapper.toEntity(video)));
    }
}
