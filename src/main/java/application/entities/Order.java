package application.entities;

import application.entities.items.Item;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private LocalDateTime creation_date;
    @Column private LocalDateTime update_date;
    @Column private Boolean active;
    @Column private String address;
    @Column private Double price;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
    @ManyToMany(mappedBy = "orders") private List<Item> items;
}