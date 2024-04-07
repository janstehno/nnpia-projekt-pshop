package application.entities.items;

import jakarta.persistence.Column;

public class Tripod extends Item {
    @Column private Double weight;
    @Column private Double carry_capacity;
    @Column private Double maximum_height;
    @Column private String color;
}