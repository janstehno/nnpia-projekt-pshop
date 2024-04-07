package application.entities.items;

import application.entities.Cart;
import application.entities.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Entity
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
    @Column private String brand;
    @Column
    @NotNull
    @NotEmpty
    private Double price;
    @Column private Integer inStorage;
    @ManyToMany
    @JoinColumn(name = "carts_id", nullable = false)
    @ToString.Exclude
    private List<Cart> carts;
    @ManyToMany
    @JoinColumn(name = "orders_id", nullable = false)
    @ToString.Exclude
    private List<Order> orders;
}