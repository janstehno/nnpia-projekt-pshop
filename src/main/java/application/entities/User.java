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
public class User {
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
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE) private Cart cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST) private List<Order> orders;
}