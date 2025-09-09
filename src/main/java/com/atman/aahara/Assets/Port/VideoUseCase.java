package com.atman.aahara.Assets.Port;

import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Assets.Domain.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface VideoUseCase {
    Video saveVideo(MultipartFile file, String folder) throws IOException;

    Video getVideo(UUID id);

    Video replaceVideo(UUID id, MultipartFile newFile, String folder) throws IOException;

    Video encodeVideo(UUID id, String encodedKey);

    Video updateRawVideo(UUID id, String rawImageKey);

    void deleteVideo(UUID id);
}
