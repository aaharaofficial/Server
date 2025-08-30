package com.atman.aahara.Assets.Image;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Inventory.Inventory;
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
@Table(name = "image")
public class Image extends BaseEntity {
    private String rawImage;
    private String encodedImage;
    private boolean isEncoded;
    @OneToOne(mappedBy = "image")
    private Inventory inventory;
}
