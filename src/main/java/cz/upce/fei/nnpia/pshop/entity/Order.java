package cz.upce.fei.nnpia.pshop.entity;

import cz.upce.fei.nnpia.pshop.entity.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private LocalDateTime creation_date;
    @Column private LocalDateTime update_date;
    @Column private Boolean active;
    @Column private String address;
    @Column private Double price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "orders") private List<Item> items;
}