package cz.upce.fei.nnpia.pshop.entity.items;

import cz.upce.fei.nnpia.pshop.entity.enums.LensE;
import cz.upce.fei.nnpia.pshop.entity.enums.MountE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lenses")
public class Lens extends Item {
    @Column public LensE type;
    @Column public MountE mount_type;
    @Column public Double min_aperture;
    @Column public Double max_aperture;
    @Column public Double min_focal_length;
    @Column public Double max_focal_length;
    @Column public Boolean built_in_stabilisation;
}