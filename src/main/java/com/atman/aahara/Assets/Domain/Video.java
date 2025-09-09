package com.atman.aahara.Assets.Domain;


import java.util.UUID;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Video {

    private UUID id;
    private String rawVideoKey;     // Original uploaded video
    private String encodedVideoKey; // Encoded video path
    private boolean encoded;
    private boolean rawVideoUploaded;

    // Domain behavior: mark video as encoded
    public void markEncoded(String encodedKey) {
        this.encodedVideoKey = encodedKey;
        this.encoded = true;
    }

    public void markRawUploaded(String rawKey) {
        this.rawVideoUploaded = true;
        this.rawVideoKey = rawKey;
    }
}
