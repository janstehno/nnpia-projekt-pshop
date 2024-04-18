package cz.upce.fei.nnpia.pshop.repository.connection;

import cz.upce.fei.nnpia.pshop.entity.connection.CartItem;
import cz.upce.fei.nnpia.pshop.entity.enums.ItemE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndItemIdAndItemType(Long cartId, Long itemId, ItemE itemType);
}