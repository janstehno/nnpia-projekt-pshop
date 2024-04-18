package cz.upce.fei.nnpia.pshop.entity.items;

import cz.upce.fei.nnpia.pshop.entity.enums.CameraE;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.entity.enums.MountE;
import cz.upce.fei.nnpia.pshop.entity.enums.SensorE;
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
@Table(name = "cameras")
public class Camera extends Item {
    @Column public CameraE type;
    @Column public MountE mount_type;
    @Column public SensorE sensor_type;
    @Column public Double weight;
    @Column public Integer minimum_iso;
    @Column public Integer maximum_iso;
    @Column public Integer af_points;
    @Column public Double fps;
    @Column public Integer resolution_width;
    @Column public Integer resolution_height;
    @Column public Double sensor_resolution_width;
    @Column public Double sensor_resolution_height;
    @Column public Boolean touch_screen;
    @Column public Boolean rotatable_screen;

    @Override
    public ItemE getItemType() {
        return ItemE.CAMERA;
    }
}