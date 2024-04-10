package cz.upce.fei.nnpia.pshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
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
    @Column private LocalDateTime creation_date;
    @Column private LocalDateTime update_date;
    @ManyToMany(fetch = FetchType.EAGER) private Set<Role> roles = new HashSet<>();
}