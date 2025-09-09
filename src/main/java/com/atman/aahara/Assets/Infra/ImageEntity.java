package com.atman.aahara.Assets.Infra;


import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Stock.Infra.Persistance.InventoryEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ImageEntity extends BaseEntity {


    @Column(name = "raw_image_key", nullable = false)
    private String rawImageKey;

    @Column(name = "encoded_image_key")
    private String encodedImageKey;

    @Column(name = "encoded", nullable = false)
    private boolean encoded;


    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    private InventoryEntity inventory;

}
