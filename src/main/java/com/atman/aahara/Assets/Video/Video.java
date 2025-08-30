package com.atman.aahara.Assets.Video;

import com.atman.aahara.Global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "video")
public class Video extends BaseEntity {
    private String rawVideo;
    private String encodedVideo;
    private boolean isEncoded;
}
