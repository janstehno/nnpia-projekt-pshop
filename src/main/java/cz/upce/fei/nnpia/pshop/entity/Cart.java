package cz.upce.fei.nnpia.pshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "shopping_carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}