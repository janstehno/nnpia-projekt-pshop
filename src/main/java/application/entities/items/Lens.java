package application.entities.items;

import jakarta.persistence.Column;

public class Lens extends Item{
    @Column private String mount_type;
    @Column private Double aperture;
    @Column private Double minimum_focal_length;
    @Column private Boolean built_in_stabilisation;
    @Column private Boolean zoom_lens;
}