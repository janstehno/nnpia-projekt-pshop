package cz.upce.fei.nnpia.pshop.entity;

import cz.upce.fei.nnpia.pshop.entity.enums.OrderE;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column private LocalDateTime creation_date;
    @Column private OrderE state;
    @Column private String street;
    @Column private String city;
    @Column private Integer zipCode;
    @Column private Integer phone;
    @Column private String shippingMethod;
    @Column private String paymentMethod;
    @Column private Double price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}