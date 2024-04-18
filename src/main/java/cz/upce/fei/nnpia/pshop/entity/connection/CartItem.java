package cz.upce.fei.nnpia.pshop.entity.connection;

import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.entity.items.Item;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "shopping_carts_items")
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column ItemE itemType;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @ToString.Exclude
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @ToString.Exclude
    private Item item;
    @Column private Integer count;
}
