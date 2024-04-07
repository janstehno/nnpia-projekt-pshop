package application.entities;

import application.entities.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private LocalDateTime creation_date;
    @Column private LocalDateTime update_date;
    @Column private Boolean active;
    @Column private String address;
    @Column private Double price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @ManyToMany(mappedBy = "itemOrders") private List<Item> items;
}