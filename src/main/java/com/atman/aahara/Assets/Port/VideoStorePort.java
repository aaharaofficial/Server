package com.atman.aahara.Assets.Port;

import com.atman.aahara.Assets.Domain.Video;

import java.util.Optional;
import java.util.UUID;

public interface VideoStorePort {

    Video save(Video video);

    Optional<Video> findById(UUID id);

    void delete(Video video);

    Video update(Video video);
}
