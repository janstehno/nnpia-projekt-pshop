package cz.upce.fei.nnpia.pshop.entity.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tripod extends Item {
    @Column private Double weight;
    @Column private Double carry_capacity;
    @Column private Double maximum_height;
    @Column private String color;
}