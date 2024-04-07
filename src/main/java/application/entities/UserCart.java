package application.entities;

import application.entities.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private AppUser appUser;
    @ManyToMany(mappedBy = "userCarts") private List<Item> items;
}