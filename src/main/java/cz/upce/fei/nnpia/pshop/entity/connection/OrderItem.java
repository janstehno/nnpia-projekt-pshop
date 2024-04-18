package cz.upce.fei.nnpia.pshop.entity.connection;

import cz.upce.fei.nnpia.pshop.entity.Order;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import cz.upce.fei.nnpia.pshop.entity.items.Item;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "orders_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column ItemE itemType;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @ToString.Exclude
    private Item item;
    @Column private Integer count;
}
