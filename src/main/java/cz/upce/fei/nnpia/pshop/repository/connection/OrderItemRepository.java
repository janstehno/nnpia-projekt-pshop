package cz.upce.fei.nnpia.pshop.repository.connection;

import cz.upce.fei.nnpia.pshop.entity.connection.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId);
}