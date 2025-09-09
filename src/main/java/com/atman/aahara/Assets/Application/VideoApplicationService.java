package com.atman.aahara.Assets.Application;

import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Assets.Domain.Video;
import com.atman.aahara.Assets.Port.FileStoragePort;
import com.atman.aahara.Assets.Port.VideoStorePort;
import com.atman.aahara.Assets.Port.VideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoApplicationService implements VideoUseCase {

    private final VideoStorePort videoStorePort;
    private final FileStoragePort fileStoragePort;

    @Override
    public Video saveVideo(MultipartFile file, String folder) throws IOException {
        // Save initial record
        Video video = Video.builder()
                .encoded(false)
                .build();
        Video savedVideo = videoStorePort.save(video);

        // Upload asynchronously, then update DB with raw key
        fileStoragePort.uploadFile(file, savedVideo.getId(), folder, s3Key -> {
            savedVideo.markRawUploaded(s3Key);
            videoStorePort.update(savedVideo);
        });

        return savedVideo;
    }

    @Override
    public Video getVideo(UUID id) {
        return videoStorePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found: " + id));
    }

    @Override
    public Video replaceVideo(UUID id, MultipartFile newFile, String folder) throws IOException {
        deleteVideo(id);
        return saveVideo(newFile, folder);
    }

    @Override
    public Video encodeVideo(UUID id, String encodedKey) {
        Video video = getVideo(id);
        video.markEncoded(encodedKey);
        return videoStorePort.update(video);
    }

    @Override
    public Video updateRawVideo(UUID id, String rawImageKey) {
        Video video = getVideo(id);
        video.markRawUploaded(rawImageKey);
        return videoStorePort.save(video);
    }

    @Override
    public void deleteVideo(UUID id) {
        Video video = getVideo(id);

        // Delete raw video first, then DB
        if (video.getRawVideoKey() != null) {
            fileStoragePort.deleteFile(video.getRawVideoKey(), () -> videoStorePort.delete(video));
        }
        // Delete encoded video if exists
        else if (video.isEncoded() && video.getEncodedVideoKey() != null) {
            fileStoragePort.deleteFile(video.getEncodedVideoKey(), () -> videoStorePort.delete(video));
        }
        // If no S3 files, just delete DB
        else {
            videoStorePort.delete(video);
        }
    }
}
