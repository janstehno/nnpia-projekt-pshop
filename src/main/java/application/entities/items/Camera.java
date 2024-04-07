package application.entities.items;

import jakarta.persistence.Column;

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