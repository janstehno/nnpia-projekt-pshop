package cz.upce.fei.nnpia.pshop.repository;

import cz.upce.fei.nnpia.pshop.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {}