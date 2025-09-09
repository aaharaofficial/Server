package com.atman.aahara.Assets.Infra;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Recipe.Base.Recipe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class VideoEntity extends BaseEntity {

    @Column(name = "raw_video_key", nullable = false)
    private String rawVideoKey;

    @Column(name = "encoded_video_key")
    private String encodedVideoKey;

    @Column(name = "encoded", nullable = false)
    private boolean encoded;
}
