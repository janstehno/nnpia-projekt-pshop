package cz.upce.fei.nnpia.pshop.repository;

import cz.upce.fei.nnpia.pshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUsername(
            @Param("name")
            String username);
}