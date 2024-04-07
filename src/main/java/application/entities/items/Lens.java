package application.entities.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lens extends Item{
    @Column private String mount_type;
    @Column private Double aperture;
    @Column private Double minimum_focal_length;
    @Column private Boolean built_in_stabilisation;
    @Column private Boolean zoom_lens;
}