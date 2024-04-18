package cz.upce.fei.nnpia.pshop.repository;

import cz.upce.fei.nnpia.pshop.entity.Role;
import cz.upce.fei.nnpia.pshop.entity.enums.RoleE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleE name);
}
