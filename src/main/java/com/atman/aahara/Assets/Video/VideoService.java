package com.atman.aahara.Assets.Video;

import com.atman.aahara.Assets.Video.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface VideoService {
    Video saveVideo(MultipartFile file, String folder) throws IOException;

    Video getVideo(UUID id);

    Video replaceVideo(Video oldVideo, MultipartFile newVideo, String folder) throws IOException;

    Video encodeVideo(UUID id, String encodedVideoKey);

    void deleteVideo(UUID id);

    void deleteVideo(Video Video);
}
