package cz.upce.fei.nnpia.pshop.repository;

import cz.upce.fei.nnpia.pshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}