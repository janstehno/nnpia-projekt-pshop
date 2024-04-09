package application.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @NotEmpty
    @Length(max = 30)
    private String name;
    @Column
    @NotNull
    @NotEmpty
    @Length(max = 30)
    private String surname;
    @Column
    @NotNull
    @NotEmpty
    @Length(max = 20)
    private String username;
    @Column
    @NotNull
    @NotEmpty
    private String password;
    @Column private Boolean active;
    @Column private LocalDateTime creation_date;
    @Column private LocalDateTime update_date;
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.REMOVE) private UserCart userCart;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.PERSIST) private List<ItemOrder> itemOrders;
}