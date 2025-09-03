package com.atman.aahara.Assets.Video;

import com.atman.aahara.Assets.FileStorageService;
import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VideoLogic implements VideoService {

    private final VideoRepository videoRepository; // ✅ lowercase for variable
    private final FileStorageService fileStorageService;

    /**
     * Create a new Video entity with raw Video key.
     */
    @Override
    public Video saveVideo(MultipartFile file, String folder) throws IOException {
        String rawVideoKey = fileStorageService.uploadFile(file, folder);

        Video video = Video.builder()
                .rawVideo(rawVideoKey)
                .isEncoded(false)
                .build();

        return videoRepository.save(video);
    }

    /**
     * Fetch Video by ID. Throws exception if not found.
     */
    @Override
    public Video getVideo(UUID id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found: " + id));
    }

    /**
     * Update Video by new Video. Throws exception if not found.
     */
    @Override
    public Video replaceVideo(Video oldVideo, MultipartFile newVideo, String folder) throws IOException {
        deleteVideo(oldVideo);
        return saveVideo(newVideo, folder);
    }

    /**
     * Encode an existing Video (set encoded key and mark as encoded)
     */
    @Override
    public Video encodeVideo(UUID id, String encodedVideoKey) {
        Video video = getVideo(id);
        video.setEncodedVideo(encodedVideoKey);
        video.setEncoded(true);
        return videoRepository.save(video);
    }

    /**
     * Delete Video by ID.
     */
    @Override
    public void deleteVideo(UUID id) {
        Video video = getVideo(id);
        deleteVideo(video);
    }

    /**
     * Delete Video by Video.
     */
    @Override
    public void deleteVideo(Video video) {
        if (video.getRawVideo() != null) {
            fileStorageService.deleteFile(video.getRawVideo());
        }
        if (video.isEncoded() && video.getEncodedVideo() != null) {
            fileStorageService.deleteFile(video.getEncodedVideo());
        }
        videoRepository.delete(video);
    }
}
