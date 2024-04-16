package cz.upce.fei.nnpia.pshop.entity.items;

import cz.upce.fei.nnpia.pshop.entity.ShoppingCart;
import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.entity.enums.BrandE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @NotEmpty
    @Length(max = 70)
    private String name;
    @Column private BrandE brand;
    @Column
    @NotNull
    @Min(0)
    private Double price;
    @Column
    @NotNull
    private Integer inStorage;
    @Column
    @NotNull
    private String thumbnail;
    @ManyToMany
    @JoinColumn(name = "carts_id", nullable = false)
    @ToString.Exclude
    private List<ShoppingCart> shoppingCarts;
    @ManyToMany
    @JoinColumn(name = "orders_id", nullable = false)
    @ToString.Exclude
    private List<Order> orders;
}