package cz.upce.fei.nnpia.pshop.entity.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Camera extends Item {
    @Column private String mount_type;
    @Column private Boolean mirrorless;
    @Column private Double weight;
    @Column private Integer minimum_iso;
    @Column private Integer maximum_iso;
    @Column private Integer sensor_resolution_width;
    @Column private Integer sensor_resolution_height;
    @Column private Boolean touch_screen;
    @Column private Boolean rotatable_screen;
}