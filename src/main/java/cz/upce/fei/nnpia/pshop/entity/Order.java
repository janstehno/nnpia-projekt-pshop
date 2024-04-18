package cz.upce.fei.nnpia.pshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @ToString.Exclude
    private User user;
}