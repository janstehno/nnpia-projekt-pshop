package application.entities;

import application.entities.items.Item;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
    @ManyToMany(mappedBy = "carts") private List<Item> items;
}