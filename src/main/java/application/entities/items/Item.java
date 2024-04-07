package application.entities.items;

import application.entities.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @NotEmpty
    @Length(max = 70)
    private String name;
    @Column private String brand;
    @Column
    @NotNull
    @NotEmpty
    private Double price;
    @Column private Integer inStorage;
    @OneToMany
    @JoinColumn(name = "carts_id", nullable = false)
    @ToString.Exclude
    private List<Cart> carts;
    @OneToMany
    @JoinColumn(name = "orders_id", nullable = false)
    @ToString.Exclude
    private List<Cart> orders;
}